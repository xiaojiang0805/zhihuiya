package com.xiaojiang.utilProject;  
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
   
public class ParseWord {  
    public static void main(String[] args) {  
        readXml("E:/test/IPC.xlsx");  
        System.out.println("-------------");  
    }  
    public static void readXml(String fileName){  
        boolean isE2007 = false;    //判断是否是excel2007格式  
        if(fileName.endsWith("xlsx"))  
            isE2007 = true;  
        try {  
            InputStream input = new FileInputStream(fileName);  //建立输入流  
            Workbook wb  = null;  
            //根据文件格式(2003或者2007)来初始化  
            if(isE2007)  
                wb = new XSSFWorkbook(input);  
            else  
                wb = new HSSFWorkbook(input);  
            Sheet sheet = wb.getSheetAt(1);     //获得第一个表单  
            
            //定义每列的数据
            String col01="";
            String col02="";
            String col03="";
            String col04="";
            String col05="";
            
            //Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
            /*while (rows.hasNext()) {  
                Row row = rows.next();  //获得行数据  
                //System.out.println("Row #" + row.getRowNum());  //获得行号从0开始  
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器  
                while (cells.hasNext()) {  
                    Cell cell = cells.next();  
                    System.out.println("Cell #" + cell.getColumnIndex());  
                }  
            }  */
            
            GBCModel model = new GBCModel();
            //获取行数
            int rownumber = sheet.getLastRowNum();
            for(int i =1;i<=rownumber;i++){
            	Row row = sheet.getRow(i);
            		System.out.println(row.getCell(0));
            		//model.setGBC(gBC);
            	}
            
            
            wb.close();
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
}  