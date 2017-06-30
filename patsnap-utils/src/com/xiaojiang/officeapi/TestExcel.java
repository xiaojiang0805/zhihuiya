package com.xiaojiang.officeapi;
	import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
	public class TestExcel {
	    public static void main(String args[]) {
	        try {
	        	 BufferedReader reader=null;
	            Runtime rt = Runtime.getRuntime();
	            Process proc = rt.exec("javac");
	            int exitVal = proc.waitFor();
	            java.io.InputStream is =  proc.getErrorStream();
	            reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));  
	            String line=null;  
	            while((line=reader.readLine())!=null){  
	                System.out.println(line);  
	            }  
	            FileWriter fw = new FileWriter(new File("a.txt"));
	            fw.write(is.toString());
	            fw.close();
	            System.out.println("Process exitValue: " + exitVal);
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	    }
	}

