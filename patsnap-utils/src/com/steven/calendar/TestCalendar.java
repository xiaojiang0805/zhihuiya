package com.steven.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestCalendar {
    
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 25);
        String tempDate = sdf.format(cal.getTime());
        System.out.println(tempDate);
    }
    
}
