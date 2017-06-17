package com.steven.reg;

import java.util.regex.Pattern;

public class regTest {
    
    public static void main(String[] args) {
        /* String content = "GCEEA00";
        
        String pattern = "([a-zA-Z]{3}(a-zA-Z]{2}([0-9]{2})?)?)";*/
        
        String content = "G01N33/543.501.A";
        String pattern = "[A-H]\\d{2}[A-Z][1-9]\\d{0,4}[:/]\\d{2,5}(\\.([A-Z0-9]+|[A-Z0-9]+[:-][A-Z0-9]+))*";
        
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
    }
    
}
