package project.Export;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import project.Helper.Asistan;
import project.Helper.Messages;
import tray.notification.NotificationType;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Excel {

    public void Export_Excel(String MusteriName, String Proje,String testyeri, String MS, String DeS, String MuayeneP,
                             String Muayene_K, String Resim_No, String Ydurum, String Muayene_A, String Sf, String Rno,
                             String Rdate, String isemrino, String teklifno, String kmesafe, String cihazadi, String mpt,
                             String miknatist, String uv, String isik, String mbolge, String akimtip, String luxmetre,
                             String mortam, String miknatisgi, String isil, String sicaklik, String alans, String yuzey,
                             String isikci, String kaldirma, String stsapma, String mdate, String aciklama,  String parca1,
                             String kontrolu1, String cap, String hatatip, String hatayeri, String sonuc1, String kaynaky,
                             String kalinlik, String Op_Name, String De_Name, String On_Name, String Op_level,
                             String De_level, String On_level, String Op_tarih, String De_tarih, String On_tarih) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("raportS.xlsx"));

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet=wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);



        XSSFRow row1 = sheet.getRow(2);

        XSSFCell cell = row1.createCell(3);
        XSSFCell cell4 = row1.createCell(19);
        XSSFCell cell9 = row1.createCell(26);
        cell.setCellValue(MusteriName);
        cell.setCellStyle(style);
        cell4.setCellValue(MuayeneP);
        cell4.setCellStyle(style);
        cell9.setCellValue(Sf);
        cell9.setCellStyle(style);


        XSSFRow row2 = sheet.getRow(3);
        XSSFCell cell0 = row2.createCell(3);
        cell0.setCellValue(Proje);
        cell0.setCellStyle(style);
        XSSFCell cell5 = row2.createCell(19);
        cell5.setCellValue(Muayene_K);
        cell5.setCellStyle(style);
        XSSFCell cell10 = row2.createCell(26);
        cell10.setCellValue(Rno);
        cell10.setCellStyle(style);

        XSSFRow row3 = sheet.getRow(4);
        XSSFCell cell1 = row3.createCell(3);
        cell1.setCellValue(testyeri);
        cell1.setCellStyle(style);
        XSSFCell cell6 = row3.createCell(19);
        cell6.setCellValue(Resim_No);
        cell6.setCellStyle(style);
        XSSFCell cell11 = row3.createCell(26);
        cell11.setCellValue(Rdate);
        cell11.setCellStyle(style);

        XSSFRow row4 = sheet.getRow(5);
        XSSFCell cell2 = row4.createCell(3);
        cell2.setCellValue(MS);
        cell2.setCellStyle(style);
        XSSFCell cell7 = row4.createCell(19);
        cell7.setCellValue(Ydurum);
        cell7.setCellStyle(style);
        XSSFCell cell12 = row4.createCell(26);
        cell12.setCellValue(isemrino);
        cell12.setCellStyle(style);

        XSSFRow row5 = sheet.getRow(6);
        XSSFCell cell3 = row5.createCell(3);
        cell3.setCellValue(DeS);
        cell3.setCellStyle(style);
        XSSFCell cell8 = row5.createCell(19);
        cell8.setCellValue(Muayene_A);
        cell8.setCellStyle(style);
        XSSFCell cell13 = row5.createCell(26);
        cell13.setCellValue(teklifno);
        cell13.setCellStyle(style);

        XSSFRow row6 = sheet.getRow(8);
        XSSFCell cell14 = row6.createCell(4);
        cell14.setCellValue(kmesafe);
        cell14.setCellStyle(style);
        XSSFCell cell20 = row6.createCell(16);
        cell20.setCellValue(mbolge);
        cell20.setCellStyle(style);
        XSSFCell cell26 = row6.createCell(25);
        cell26.setCellValue(sicaklik);
        cell26.setCellStyle(style);

        XSSFRow row7 = sheet.getRow(9);
        XSSFCell cell15 = row7.createCell(4);
        cell15.setCellValue(cihazadi);
        cell.setCellStyle(style);
        XSSFCell cell21 = row7.createCell(16);
        cell21.setCellValue(akimtip);
        cell21.setCellStyle(style);
        XSSFCell cell27 = row7.createCell(25);
        cell27.setCellValue(alans);
        cell27.setCellStyle(style);

        XSSFRow row8 = sheet.getRow(10);
        XSSFCell cell16 = row8.createCell(4);
        cell16.setCellValue(mpt);
        cell16.setCellStyle(style);
        XSSFCell cell22 = row8.createCell(16);
        cell22.setCellValue(luxmetre);
        cell22.setCellStyle(style);

        XSSFRow row9 = sheet.getRow(11);
        XSSFCell cell17 = row9.createCell(4);
        cell17.setCellValue(miknatist);
        cell17.setCellStyle(style);
        XSSFCell cell23 = row9.createCell(16);
        cell23.setCellValue(mortam);
        cell23.setCellStyle(style);
        XSSFCell cell28 = row9.createCell(25);
        cell28.setCellValue(yuzey);
        cell28.setCellStyle(style);

        XSSFRow row10= sheet.getRow(12);
        XSSFCell cell18 = row10.createCell(4);
        cell18.setCellValue(uv);
        cell18.setCellStyle(style);
        XSSFCell cell24 = row10.createCell(16);
        cell24.setCellValue(miknatisgi);
        cell24.setCellStyle(style);
        XSSFCell cell29 = row10.createCell(25);
        cell29.setCellValue(isikci);
        cell29.setCellStyle(style);

        XSSFRow row11 = sheet.getRow(13);
        XSSFCell cell19 = row11.createCell(4);
        cell19.setCellValue(isik);
        cell19.setCellStyle(style);
        XSSFCell cell25 = row11.createCell(16);
        cell25.setCellValue(isil);
        cell25.setCellStyle(style);
        XSSFCell cell30 = row11.createCell(25);
        cell30.setCellValue(kaldirma);
        cell30.setCellStyle(style);


        XSSFRow row12 = sheet.getRow(19);
        XSSFCell cell31 = row12.createCell(7);
        cell31.setCellValue(stsapma);
        cell31.setCellStyle(style);

        XSSFRow row13 = sheet.getRow(20);
        XSSFCell cell32 = row13.createCell(7);
        cell32.setCellValue(mdate);
        cell32.setCellStyle(style);

        XSSFRow row14 = sheet.getRow(21);
        XSSFCell cell33 = row14.createCell(7);
        cell33.setCellValue(aciklama);
        cell33.setCellStyle(style);

        //SONUÇLAR
        XSSFRow row24 = sheet.getRow(24);
        XSSFCell cell34 = row24.createCell(1);
        cell34.setCellValue(parca1);
        cell34.setCellStyle(style);
        XSSFCell cell35 = row24.createCell(8);
        cell35.setCellValue(kontrolu1);
        cell35.setCellStyle(style);
        XSSFCell cell36 = row24.createCell(11);
        cell36.setCellValue(kaynaky);
        cell36.setCellStyle(style);
        XSSFCell cell37 = row24.createCell(17);
        cell37.setCellValue(kalinlik);
        cell37.setCellStyle(style);
        XSSFCell cell38 = row24.createCell(18);
        cell38.setCellValue(cap);
        cell38.setCellStyle(style);
        XSSFCell cell39 = row24.createCell(22);
        cell39.setCellValue(hatatip);
        cell39.setCellStyle(style);
        XSSFCell cell40 = row24.createCell(24);
        cell40.setCellValue(hatayeri);
        cell40.setCellStyle(style);
        XSSFCell cell41 = row24.createCell(27);
        cell41.setCellValue(sonuc1);
        cell41.setCellStyle(style);


        XSSFRow row39 = sheet.getRow(39);
        XSSFCell cell42 = row39.createCell(5);
        cell42.setCellValue(Op_Name);
        cell42.setCellStyle(style);
        XSSFCell cell43 = row39.createCell(15);
        cell43.setCellValue(De_Name);
        cell43.setCellStyle(style);
        XSSFCell cell44 = row39.createCell(20);
        cell44.setCellValue(On_Name);

        XSSFRow row40 = sheet.getRow(40);
        XSSFCell cell45 = row40.createCell(5);
        cell45.setCellValue(Op_level);
        cell45.setCellStyle(style);
        XSSFCell cell46 = row40.createCell(15);
        cell46.setCellValue(De_level);
        cell46.setCellStyle(style);
        XSSFCell cell47 = row40.createCell(20);
        cell47.setCellValue(On_level);
        cell47.setCellStyle(style);

        XSSFRow row41 = sheet.getRow(41);
        XSSFCell cell48 = row41.createCell(5);
        cell48.setCellStyle(style);
        cell48.setCellValue(Op_tarih);
        XSSFCell cell49 = row41.createCell(15);
        cell49.setCellValue(De_tarih);
        cell49.setCellStyle(style);
        XSSFCell cell50 = row41.createCell(20);
        cell50.setCellValue(On_tarih);
        cell50.setCellStyle(style);


        FileOutputStream outputStream=new FileOutputStream("C:\\Users\\busra\\IdeaProjects\\INF202\\Project\\src\\project\\Files\\ExcelFiles\\"+ Rno + ".Manyetik Parçacık Raporu.xlsx");
        wb.write(outputStream);

        inputStream.close();
        wb.close();
        outputStream.close();
        Messages.TrayMessage("Dışarı Aktarma İşlemi", "Excel Dosyası Başarıyla Oluşturuldu", NotificationType.SUCCESS);
    }
}







  /*  public static void main(String[] args) throws IOException {
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
}*/

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





    /*

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

