package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class excelUtility {
    public FileInputStream fis;
    public FileOutputStream fos;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle cstyle;
    String path;

    public excelUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        wb.close();
        fis.close();

        return  rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fis.close();

        return cellCount;

    }
    public String getCellData(String sheetName, int rowNum, int column) throws IOException {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(column);

        DataFormatter formatter = new DataFormatter();
        String data;
        try{
            //return the formatted value of a string
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data="";
        }
        wb.close();
        fis.close();
        return data;
    }
    public void setCellData(String sheetName, int rowNum, int column, String data) throws IOException {
        File excelFile = new File(path);
        if(!excelFile.exists()){
            wb=new XSSFWorkbook();
            fos = new FileOutputStream(path);
            wb.write(fos);
        }
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);

        if(wb.getSheetIndex(sheetName)==-1){
            wb.createSheet(sheetName);
        }
        sheet = wb.getSheet(sheetName);

        if(sheet.getRow(rowNum)==null){
            sheet.createRow(rowNum);
        }
        row = sheet.getRow(rowNum);

        cell = row.createCell(column);
        cell.setCellValue(data);

        fos = new FileOutputStream(path);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }
}
