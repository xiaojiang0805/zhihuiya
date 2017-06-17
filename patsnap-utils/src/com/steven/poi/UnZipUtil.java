package com.steven.poi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class UnZipUtil {
    /** 
     * 解压缩zip包 
     * @param zipFilePath zip文件的全路径 
     * @param unzipFilePath 解压后的文件保存的路径 
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含 
     */
    @SuppressWarnings("unchecked")
    public static void unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName)
        throws Exception {
        if (zipFilePath.isEmpty() || unzipFilePath.isEmpty()) {
            throw new Exception("the path of zip is null");
        }
        File zipFile = new File(zipFilePath);
        //如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径  
        //创建解压缩文件保存的路径  
        File unzipFileDir = new File(unzipFilePath);
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory()) {
            unzipFileDir.mkdirs();
        }
        
        //开始解压  
        ZipEntry entry = null;
        String entryFilePath = null, entryDirPath = null;
        File entryFile = null, entryDir = null;
        int index = 0, count = 0, bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>)zip.entries();
        //循环对压缩包里的每一个文件进行解压       
        while (entries.hasMoreElements()) {
            entry = entries.nextElement();
            //构建压缩包中一个文件解压后保存的文件全路径  
            entryFilePath = unzipFilePath + File.separator + entry.getName();
            //构建解压后保存的文件夹路径  
            index = entryFilePath.lastIndexOf(File.separator);
            if (index != -1) {
                entryDirPath = entryFilePath.substring(0, index);
            }
            else {
                entryDirPath = "";
            }
            entryDir = new File(entryDirPath);
            //如果文件夹路径不存在，则创建文件夹  
            if (!entryDir.exists() || !entryDir.isDirectory()) {
                entryDir.mkdirs();
            }
            
            //创建解压文件  
            entryFile = new File(entryFilePath);
            if (entryFile.exists()) {
                //检测文件是否允许删除，如果不允许删除，将会抛出SecurityException  
                SecurityManager securityManager = new SecurityManager();
                securityManager.checkDelete(entryFilePath);
                //删除已存在的目标文件  
                entryFile.delete();
            }
            
            //写入文件  
            bos = new BufferedOutputStream(new FileOutputStream(entryFile));
            bis = new BufferedInputStream(zip.getInputStream(entry));
            while ((count = bis.read(buffer, 0, bufferSize)) != -1) {
                bos.write(buffer, 0, count);
            }
            bos.flush();
            bos.close();
        }
    }
    
    public static void unZip(String src, String target)
        throws IOException {
        ZipInputStream zipIn = null;
        ZipFile zip = null;
        try {
            zipIn = new ZipInputStream(new FileInputStream(src));
            ZipEntry in = null;
            zip = new ZipFile(src);
            while ((in = zipIn.getNextEntry()) != null) {
                File file = new File(target + in.getName());
                if (in.getName().endsWith("/")) {
                    file.mkdirs();
                }
                else {
                    byte[] b = IOUtil.getByteByInputStream(zip.getInputStream(in));
                    if (!new File(file.getParent()).exists()) {
                        new File(file.getParent()).mkdirs();
                    }
                    FileOutputStream fout = new FileOutputStream(file);
                    fout.write(b);
                    fout.close();
                }
            }
            zip.close();
        }
        finally {
            if (zipIn != null) {
                zipIn.close();
            }
            if (zip != null) {
                zip.close();
            }
        }
    }
    
    public static void main(String[] args)
        throws Exception {
        unZip("April 2012 2.zip", "E:/tmp/");
    }
}
