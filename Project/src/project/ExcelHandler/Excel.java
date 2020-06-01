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


public class Excel {
    public static void main(String[] args) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(
                new FileInputStream("raport.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fs, true);
        HSSFSheet sheet1 = wb.getSheetAt(0);
        HSSFRow row = sheet1.getRow(2);
        HSSFCell cell = row.getCell(3);
        cell.setCellValue("Büşra");
        HSSFRow rows = sheet1.getRow(3);
        HSSFCell cells = rows.getCell(3);
        cells.setCellValue("SARI");
        FileOutputStream outputStream = new FileOutputStream("A.xls");
        wb.write(outputStream);
        wb.close();
        outputStream.close();
        System.out.println("Excel written successfully");
    }
}

      /*  URL url = Thread.currentThread().getContextClassLoader().getResource("C:\\Users\\busra\\Desktop\\Project\\raport.xls");
        File file = new File(url.getPath());
        try (FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\busra\\Desktop\\ExcelDenemesi\\Kopya.xls")) {

            Files.copy(file.toPath(), fileOutputStream);

            Workbook workbook = new HSSFWorkbook();
            workbook.write(fileOutputStream);
        }
        FileInputStream inp = new FileInputStream("C:\\Users\\busra\\Desktop\\ExcelDenemesi\\Kopya.xls");

        HSSFWorkbook wb= new HSSFWorkbook(inp,true);
        HSSFSheet sheet1=wb.getSheetAt(0);
        HSSFRow row=sheet1.getRow(2);
        HSSFCell cell =row.getCell(3);
        cell.setCellValue("Büşra");
        HSSFRow rows=sheet1.getRow(3);
        HSSFCell cells =rows.getCell(3);
        cells.setCellValue("SARI");


        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\busra\\Desktop\\ExcelDenemesi\\raport.xls");
        wb.write(outputStream);
        wb.close();
        outputStream.close();
        System.out.println("Excel written successfully");

    }
} */





    /*  public void Export_Excel(String MusteriName, String Proje, String MS, String DeS, String MuayeneP, String Muayene_K, String Resim_No, String Ydurum, String Muayene_A, String Sf, String Rno, String Rdate, String isemrino, String teklifno, String kmesafe, String cihazadi, String mpt, String miknatist, String uv, String isik, String mbolge, String akimtip, String luxmetre, String mortam, String miknatisgi, String isil, String sicaklik, String alans, String yuzey, String isikci, String kaldirma, String stsapma, String mdate, String aciklama, String parca1, String kontrolu1, String sonuc1, String kaynaky, String kalinlik, String Op_Name, String De_Name, String On_Name) throws IOException {
    POIFSFileSystem fs= new POIFSFileSystem(
                new FileInputStream("raport.xls"));
        HSSFWorkbook wb= new HSSFWorkbook(fs,true);
        HSSFSheet sheet=wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(2);
        HSSFCell cell = row.createCell(3);
        cell.setCellValue(MusteriName);
        HSSFRow row1 = sheet.getRow(3);
        HSSFCell cell1 = row.createCell(3);
        cell.setCellValue(Proje);
        HSSFRow row2 = sheet.getRow(2);
        HSSFCell cell2 = row.createCell(3);
        cell.setCellValue(MusteriName);
        HSSFRow row3 = sheet.getRow(2);
        HSSFCell cell3 = row.createCell(3);
        cell.setCellValue(Proje);
        HSSFRow row4 = sheet.getRow(2);
        HSSFCell cell4 = row.createCell(3);
        cell.setCellValue(MS);
        HSSFRow row5 = sheet.getRow(2);
        HSSFCell cell5 = row.createCell(3);
        cell.setCellValue(DeS);
        HSSFRow row6 = sheet.getRow(2);
        HSSFCell cell6 = row.createCell(3);
        cell.setCellValue(MusteriName);
        HSSFRow row7 = sheet.getRow(3);
        HSSFCell cell7 = row.createCell(3);
        cell.setCellValue(Proje);
        HSSFRow row8 = sheet.getRow(2);
        HSSFCell cell8 = row.createCell(3);
        cell.setCellValue(MusteriName);
        HSSFRow row9 = sheet.getRow(2);
        HSSFCell cell9 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row10 = sheet.getRow(2);
        HSSFCell cell10 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row11 = sheet.getRow(2);
        HSSFCell cell11 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row12 = sheet.getRow(2);
        HSSFCell cell12 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row13 = sheet.getRow(2);
        HSSFCell cell13 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row14 = sheet.getRow(2);
        HSSFCell cell14 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row15 = sheet.getRow(2);
        HSSFCell cell15 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row16 = sheet.getRow(2);
        HSSFCell cell16 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row17 = sheet.getRow(2);
        HSSFCell cell17 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row18 = sheet.getRow(2);
        HSSFCell cell18 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row19 = sheet.getRow(2);
        HSSFCell cell19 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row20 = sheet.getRow(2);
        HSSFCell cell20 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row21 = sheet.getRow(2);
        HSSFCell cell21 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row22 = sheet.getRow(2);
        HSSFCell cell22 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row23 = sheet.getRow(2);
        HSSFCell cell23 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row24 = sheet.getRow(2);
        HSSFCell cell24 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row25 = sheet.getRow(2);
        HSSFCell cell25 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row26 = sheet.getRow(2);
        HSSFCell cell26 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row27 = sheet.getRow(2);
        HSSFCell cell27 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row28 = sheet.getRow(2);
        HSSFCell cell28 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row29 = sheet.getRow(2);
        HSSFCell cell29 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row30 = sheet.getRow(2);
        HSSFCell cell30 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row31 = sheet.getRow(2);
        HSSFCell cell31 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row32 = sheet.getRow(2);
        HSSFCell cell32 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row33 = sheet.getRow(2);
        HSSFCell cell33 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row34 = sheet.getRow(2);
        HSSFCell cell34 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row35 = sheet.getRow(2);
        HSSFCell cell35 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row36 = sheet.getRow(2);
        HSSFCell cell36 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row37 = sheet.getRow(2);
        HSSFCell cell37 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row38 = sheet.getRow(2);
        HSSFCell cell38 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row39 = sheet.getRow(2);
        HSSFCell cell39 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row40 = sheet.getRow(2);
        HSSFCell cell40 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row41 = sheet.getRow(2);
        HSSFCell cell41 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row42 = sheet.getRow(2);
        HSSFCell cell42 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row43 = sheet.getRow(2);
        HSSFCell cell43 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row44 = sheet.getRow(2);
        HSSFCell cell44 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row45 = sheet.getRow(2);
        HSSFCell cell45 = row.createCell(3);
        cell.setCellValue();
        HSSFRow row46 = sheet.getRow(2);
        HSSFCell cell46 = row.createCell(3);
        cell.setCellValue();

    }
}

   /* public static void main(String[] args) throws IOException, OpenXML4JException, XmlException {
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


/* */

