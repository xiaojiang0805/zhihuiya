package com.steven.phantomjs;
  

import java.io.*;     
         
public class HttpUtilsTest {     
    public static String getAjaxCotnent(String url) throws IOException, Exception {     
        Runtime rt = Runtime.getRuntime();     
       // Process p = rt.exec("javac");//这里我的codes.js是保存在c盘下面的phantomjs目录     
        Process p = rt.exec("phantomjs.exe C:\\Users\\Administrator\\workspace\\patsnap-utils\\code.js "+url);//这里我的codes.js是保存在c盘下面的phantomjs目录     
        p.waitFor();
        InputStream is = p.getErrorStream();
//        InputStream is = p.getInputStream();     
        BufferedReader br = new BufferedReader(new InputStreamReader(is));     
        StringBuffer sbf = new StringBuffer();     
        String tmp = "";     
        while((tmp = br.readLine())!=null){     
            sbf.append(tmp);     
        }     
        System.out.println(sbf.toString());     
        System.out.println("end..");     
        return sbf.toString();     
    }     
     
    public static void main(String[] args) throws Exception {     
        getAjaxCotnent("http://www.baidu.com");     
    }     
}    


