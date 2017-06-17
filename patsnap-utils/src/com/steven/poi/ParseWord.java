package com.steven.poi;

import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ParseWord {
    
    public static void main(String[] args)
        throws Exception {
        /*
         * FileInputStream is = new FileInputStream("E:/Mar_2017_16A.doc");
         * WordExtractor wordExtractor = new WordExtractor(is); wordExtractor.T
         */
        
        /*InputStream is = new FileInputStream(new File("2003.doc"));
        WordExtractor ex = new WordExtractor(is);
        String text2003 = ex.getText();
        System.out.println(text2003);*/
        
        String str = "ctl00$m$g_cc0a4448_5aa3_4c04_b708_4c9cf0e8b69b$btnSearchWPQ8";
        String[] temp = str.split("[$]");
        System.out.println(temp[2]);
        //testWord2();
    }
    
    public static void testWord() {
        try {
            FileInputStream in = new FileInputStream("2003.doc");// �����ĵ�
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();// �õ��ĵ��Ķ�ȡ��Χ
            TableIterator it = new TableIterator(range);
            // �����ĵ��еı��
            while (it.hasNext()) {
                Table tb = (Table)it.next();
                // �����У�Ĭ�ϴ�0��ʼ
                for (int i = 0; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    // �����У�Ĭ�ϴ�0��ʼ
                    for (int j = 0; j < tr.numCells(); j++) {
                        TableCell td = tr.getCell(j);// ȡ�õ�Ԫ��
                        // ȡ�õ�Ԫ�������
                        for (int k = 0; k < td.numParagraphs(); k++) {
                            Paragraph para = td.getParagraph(k);
                            String s = para.text();
                            System.out.println(s);
                        } // end for
                    } // end for
                } // end for
            } // end while
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testWord2() {
        try {
            FileInputStream in = new FileInputStream("Mar_2017_16A.doc");// �����ĵ�
            //			FileInputStream in = new FileInputStream("2003.doc");// �����ĵ�
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();// �õ��ĵ��Ķ�ȡ��Χ
            TableIterator it = new TableIterator(range);
            // �����ĵ��еı��
            while (it.hasNext()) {
                Table tb = (Table)it.next();
                // �����У�Ĭ�ϴ�0��ʼ
                if (tb.numRows() > 0) {
                    TableRow tr = tb.getRow(0);
                    // �����У�Ĭ�ϴ�0��ʼ
                    if (tr.numCells() == 2) {
                        TableCell td1 = tr.getCell(0);// ȡ�õ�Ԫ��
                        TableCell td2 = tr.getCell(1);// ȡ�õ�Ԫ��
                        // ȡ�õ�Ԫ�������
                        String str1 = td1.text().trim();
                        String str2 = td2.text().trim();
                        if (str2 != null && !"".equals(str2) && str2.contains("[21][11]")) {
                            System.out.println(str1);
                        }
                    } // end for
                } // end for
            } // end while
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
