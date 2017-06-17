package com.steven.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    
    public static void main(String[] args)
        throws ParseException {
        int year = 2017;
        String y = "20150106";
        
        getStartDateAndEndDateOfMonth(y);
    }
    
    public static int[] getStartDateAndEndDateOfMonth(String strDate)
        throws ParseException {
        int[] temp;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse(strDate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        for (int i = 1; i <= 12; i++) {
            //获取哪个月
            c.set(Calendar.MONTH, i - 1);
            //获取当前月第一天：
            c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
            String startDate = format.format(c.getTime());
            
            //获取当前月最后一天
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            String endDate = format.format(c.getTime());
            
            System.out.println("startDate =" + startDate + " :  endDate = " + endDate);
            System.out.println("********************");
        }
        return null;
    }
    
}
