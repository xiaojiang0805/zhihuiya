package snippet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ILListSpider {
    private String tempBaseDir = "E:/test";
    
    public static Logger logger = Logger.getLogger(ILListSpider.class);
    
    public ArrayList<String> pnList = new ArrayList<String>();
    
    private int parseDocumentsAndGetList(String baseDir) {
        List<String> docNameList = getFileNameList(baseDir);
        if (docNameList != null) {
            for (String fileName : docNameList) {
                parseDocGetList(baseDir, fileName);
            }
        }
        return pnList.size();
    }
    
    public void parseDocGetList(String baseDir, String file) {
        try {
            ArrayList<String> list = new ArrayList<String>();
            FileInputStream in = new FileInputStream(baseDir + "/" + file);// load document file.
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();// get document read range.
            TableIterator it = new TableIterator(range);
            // iterator 
            while (it.hasNext()) {
                Table tb = (Table)it.next();
                // iterator row.default is 0.
                if (tb.numRows() > 0) {
                    TableRow tr = tb.getRow(0);
                    if (tr.numCells() == 2) {
                        TableCell td1 = tr.getCell(0);// get cell
                        TableCell td2 = tr.getCell(1);
                        // get cell's value.
                        String str1 = td1.text().trim();
                        String str2 = td2.text().trim();
                        if (str2 != null && !"".equals(str2) && str2.contains("[21][11]")) {
                            String pn = "";
                            if (str1.length() >= 6) {
                                pn = str1.substring(str1.length() - 6, str1.length());
                            }
                            else {
                                System.out.println(str1 + " file name:" + file);
                            }
                            Pattern pattern = Pattern.compile("[0-9]{6}");
                            Matcher matcher = pattern.matcher(pn);
                            if (matcher.matches()) {
                                list.add(pn);
                            }
                        }
                    }
                    else if (tr.numCells() == 3) {
                        TableCell td2 = tr.getCell(1);
                        String str2 = td2.text().trim();
                        if (str2.length() >= 6) {
                            String pn = str2.substring(str2.length() - 6, str2.length());
                            Pattern pattern = Pattern.compile("[0-9]{6}");
                            Matcher matcher = pattern.matcher(pn);
                            if (matcher.matches()) {
                                list.add(pn);
                            }
                        }
                        
                    }
                }
            } //end while.
            pnList.addAll(list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getFileNameList(String baseDir) {
        List<String> docNameList = new ArrayList<String>();
        String path = baseDir;
        File f = new File(path);
        if (!f.exists()) {
            logger.warn(path + " not exists");
            return null;
        }
        
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (!fs.isDirectory() && fs.getName().endsWith(".doc")) {
                logger.info("the file is :" + fs.getName());
                docNameList.add(fs.getName());
            }
        }
        return docNameList;
    }
    
    private void parseAndDownload(String filePath)
        throws Exception {
        List<String> docList = new ArrayList<String>();
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        
        File dir = new File(tempBaseDir);
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                f.delete();
            }
        }
        
        while ((line = br.readLine()) != null) {
            docList.add(line);
        }
        //download
        for (String url : docList) {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            downloadFile(url, fileName);
        }
        
        br.close();
    }
    
    public void downloadFile(String fileUrl, String fileLocal)
        throws Exception {
        
        URL url = new URL(fileUrl.replaceAll(" ", "%20"));
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("file read failure!");
        }
        
        File tempFile = new File(tempBaseDir);
        if (!tempFile.exists() || (tempFile.exists() && tempFile.isFile())) {
            tempFile.mkdir();
        }
        
        //读文件流
        DataInputStream in = new DataInputStream(urlCon.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(tempBaseDir + "/" + fileLocal));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        in.close();
    }
    
    public static void main(String[] args)
        throws Exception {
        ILListSpider ilList = new ILListSpider();
        //ilList.parseAndDownload("history_data.txt");   //下载
        
        //解析及保存
        String filename = "DESIGNVIEW-" + "IL" + "data-zip-count16-20122013.txt";
        File outputFile = new File(filename);
        FileWriter outfilewriter = new FileWriter(outputFile);
        //...
        if (outputFile.exists()) {
            outputFile.delete();
        }
        
        int sum = ilList.parseDocumentsAndGetList(ilList.tempBaseDir);
        
        for (String str : ilList.pnList) {
            outfilewriter.write(str + "\n");
        }
        
        outfilewriter.close();
    }
}
