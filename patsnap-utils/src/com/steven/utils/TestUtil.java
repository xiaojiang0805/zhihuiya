package com.steven.utils;

public class TestUtil {
    
    public static void main(String[] args) {
        String str =
            "AT,BG,BX,CA,CY,CZ,DK,EE,EM,ES,FR,GB,GR,HR,IS,KR,LT,LV,MT,MX,NO,RO,RU,SE,SI,SK,TN,AL,AP,BR,CO,MA,MK,PL";
        String[] split = str.split(",");
        String temp = "";
        for (int i = 0; i < split.length; i++) {
            temp = temp + "\"" + split[i] + "\",";
        }
        System.out.println(temp);
        
        String SPLIT = Character.toString((char)0x2);
        String KV = Character.toString((char)0x3);
        System.out.println(SPLIT);
        System.out.println(KV);
        String logic_pct_apdt = "123";
        System.out
            .println(new User().getName() + "\n" + "pct.apdt must less than or equal apdt,pct.apdt= " + logic_pct_apdt);
    }
    
}
