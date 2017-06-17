package com.steven.utils;

import java.io.File;

public class TestCreateDir {
    
    public static void main(String[] args) {
        
        String filePath = "G:/test1/test2/test3";
        File fp = new File(filePath);
        // 目录已存在创建文件夹  
        if (!fp.exists()) {
            // 目录不存在的情况下，会抛出异常  
            fp.mkdirs();
        }
        System.out.println("执行结束" + filePath);
    }
    
}
