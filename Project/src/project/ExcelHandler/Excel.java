package project.ExcelHandler;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;

public class Excel {
    public static void main(String[] args) throws IOException, OpenXML4JException, XmlException {
        OPCPackage pkg = OPCPackage.open(new File("raportS.xlsx"));
        POIXMLProperties props = new POIXMLProperties(pkg);
       // POIFSFileSystem inputStream= new POIFSFileSystem(
         //       new FileInputStream("raportS.xlsx"));
        //FileInputStream inputStream = new FileInputStream("raportS.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(String.valueOf(props));
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(2);
        XSSFCell cell = row.createCell(3);
        cell.setCellValue("ARSLAN GEMİ");
        XSSFRow row2 = sheet.getRow(3);
        XSSFCell cell2 = row2.createCell(3);
        cell2.setCellValue("ARSLAN PROJE");
        XSSFRow row3 = sheet.getRow(4);
        XSSFCell cell3 = row3.createCell(3);
        cell3.setCellValue("İSTANBUL");
        FileOutputStream outputStream = new FileOutputStream("deneme1.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        System.out.println("Excel written successfully");


/*POIFSFileSystem fs= new POIFSFileSystem(
        new FileInputStream("raportS.xlsx"));
HSSFWorkbook wb= new HSSFWorkbook(fs,true);
HSSFSheet sheet1=wb.getSheetAt(0);
HSSFRow row=sheet1.getRow(2);
HSSFCell cell =row.getCell(3);
cell.setCellValue("Büşra");
        HSSFRow rows=sheet1.getRow(3);
        HSSFCell cells =rows.getCell(3);
        cells.setCellValue("SARI");
FileOutputStream fileout= new FileOutputStream("NEW7.xlsx");
wb.write(fileout);
fileout.close();
*/
    }
}
