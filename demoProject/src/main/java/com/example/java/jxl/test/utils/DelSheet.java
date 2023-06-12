package com.example.java.jxl.test.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

//import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DelSheet {
    /** 
     * ɾ��ָ����Sheet 
     * @param targetFile  Ŀ���ļ� 
     * @param sheetName   Sheet���� 
     */ 
    public static void deleteSheet(String targetFile,String sheetName) {
        try { 
            FileInputStream fis = new FileInputStream(targetFile); 
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            fileWrite(targetFile, wb); 
            //ɾ��Sheet 
            wb.removeSheetAt(wb.getSheetIndex(sheetName)); 
            fileWrite(targetFile, wb); 
            fis.close(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
    
  /*  *
     * д����/ɾ�����Excel�ļ� 
     * @param targetFile  Ŀ���ļ� 
     * @param wb          Excel���� 
     * @throws Exception */

    public static void fileWrite(String targetFile,HSSFWorkbook wb) throws Exception{
        FileOutputStream fileOut = new FileOutputStream(targetFile); 
        wb.write(fileOut); 
        fileOut.flush(); 
        fileOut.close(); 
    }

}