package com.steven.poi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFile {
    
    public static void main(String[] args)
        throws Exception {
        String fileUrl = "http://www.justice.gov.il/Units/RashamHaptentim/Units/patent/yoman/April 2012 2.zip";
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        URL url = new URL(fileUrl.replaceAll(" ", "%20"));
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("file read failure!");
        }
        
        //读文件流
        DataInputStream in = new DataInputStream(urlCon.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        in.close();
    }
    
}
