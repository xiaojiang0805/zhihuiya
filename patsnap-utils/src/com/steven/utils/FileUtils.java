package com.steven.utils;

import java.io.File;
import java.io.FileReader;

public class FileUtils {
    
    public static void main(String[] args)
        throws Exception {
        String url = "";
        File file = new File(url);
        for (File f : file.listFiles()) {
            FileReader fr = new FileReader(f);
            
        }
    }
    
}
