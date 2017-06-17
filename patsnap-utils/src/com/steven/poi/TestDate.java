package com.steven.poi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    
    public static void main(String[] args) {
        SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
        String today = "20170425";
        String target = formate.format(new Date(today));
        System.out.println(target);
    }
    
}
