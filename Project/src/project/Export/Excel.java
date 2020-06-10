package project.Export;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;


public class Excel {
    public void Export_Excel(String MusteriName, String Proje,String testyeri, String MS, String DeS, String MuayeneP, String Muayene_K, String Resim_No, String Ydurum, String Muayene_A, String Sf, String Rno, String Rdate, String isemrino, String teklifno, String kmesafe, String cihazadi, String mpt, String miknatist, String uv, String isik, String mbolge, String akimtip, String luxmetre, String mortam, String miknatisgi, String isil, String sicaklik, String alans, String yuzey, String isikci, String kaldirma, String stsapma, String mdate, String aciklama, String parca1,String kontrolu1, String cap, String hatatip, String hatayeri, String sonuc1, String kaynaky, String kalinlik, String Op_Name, String De_Name, String On_Name) throws IOException {
        POIFSFileSystem fs= new POIFSFileSystem(
                new FileInputStream("raport.xls"));
        HSSFWorkbook wb= new HSSFWorkbook(fs,true);
        HSSFSheet sheet=wb.getSheetAt(0);

        HSSFRow row = sheet.getRow(2);
        HSSFCell cell = row.createCell(3);
        cell.setCellValue(MusteriName);

        HSSFRow row0 = sheet.getRow(3);
        HSSFCell cell0 = row0.createCell(3);
        cell.setCellValue(Proje);

        HSSFRow row1 = sheet.getRow(4);
        HSSFCell cell1 = row1.createCell(3);
        cell.setCellValue(testyeri);

        HSSFRow row2 = sheet.getRow(5);
        HSSFCell cell2 = row2.createCell(3);
        cell.setCellValue(MS);

        HSSFRow row3 = sheet.getRow(6);
        HSSFCell cell3 = row3.createCell(3);
        cell.setCellValue(DeS);

        HSSFRow row4 = sheet.getRow(2);
        HSSFCell cell4 = row3.createCell(19);
        cell.setCellValue(MuayeneP);

        HSSFRow row5 = sheet.getRow(3);
        HSSFCell cell5 = row4.createCell(19);
        cell.setCellValue(Muayene_K);

        HSSFRow row6 = sheet.getRow(4);
        HSSFCell cell6 = row.createCell(19);
        cell.setCellValue(Resim_No);

        HSSFRow row7 = sheet.getRow(5);
        HSSFCell cell7 = row.createCell(19);
        cell.setCellValue(Ydurum);

        HSSFRow row8 = sheet.getRow(6);
        HSSFCell cell8 = row.createCell(19);
        cell.setCellValue(Muayene_A);

        HSSFRow row9 = sheet.getRow(2);
        HSSFCell cell9 = row.createCell(26);
        cell.setCellValue(Sf);

        HSSFRow row10 = sheet.getRow(3);
        HSSFCell cell10 = row.createCell(26);
        cell.setCellValue(Rno);

        HSSFRow row11 = sheet.getRow(4);
        HSSFCell cell11 = row.createCell(26);
        cell.setCellValue(Rdate);

        HSSFRow row12 = sheet.getRow(5);
        HSSFCell cell12 = row.createCell(26);
        cell.setCellValue(isemrino);

        HSSFRow row13 = sheet.getRow(6);
        HSSFCell cell13 = row.createCell(26);
        cell.setCellValue(teklifno);

        HSSFRow row14 = sheet.getRow(8);
        HSSFCell cell14 = row.createCell(4);
        cell.setCellValue(kmesafe);

        HSSFRow row15 = sheet.getRow(9);
        HSSFCell cell15 = row.createCell(4);
        cell.setCellValue(cihazadi);

        HSSFRow row16 = sheet.getRow(10);
        HSSFCell cell16 = row.createCell(4);
        cell.setCellValue(mpt);

        HSSFRow row17 = sheet.getRow(11);
        HSSFCell cell17 = row.createCell(4);
        cell.setCellValue(miknatist);

        HSSFRow row18 = sheet.getRow(12);
        HSSFCell cell18 = row.createCell(4);
        cell.setCellValue(uv);

        HSSFRow row19 = sheet.getRow(13);
        HSSFCell cell19 = row.createCell(4);
        cell.setCellValue(isik);

        HSSFRow row20 = sheet.getRow(8);
        HSSFCell cell20 = row.createCell(16);
        cell.setCellValue(mbolge);

        HSSFRow row21 = sheet.getRow(9);
        HSSFCell cell21 = row.createCell(16);
        cell.setCellValue(akimtip);

        HSSFRow row22 = sheet.getRow(10);
        HSSFCell cell22 = row.createCell(16);
        cell.setCellValue(luxmetre);

        HSSFRow row23 = sheet.getRow(11);
        HSSFCell cell23 = row.createCell(16);
        cell.setCellValue(mortam);

        HSSFRow row24 = sheet.getRow(12);
        HSSFCell cell24 = row.createCell(16);
        cell.setCellValue(miknatisgi);

        HSSFRow row25 = sheet.getRow(13);
        HSSFCell cell25 = row.createCell(16);
        cell.setCellValue(isil);

        HSSFRow row26 = sheet.getRow(8);
        HSSFCell cell26 = row.createCell(25);
        cell.setCellValue(sicaklik);

        HSSFRow row27 = sheet.getRow(9);
        HSSFCell cell27 = row.createCell(25);
        cell.setCellValue(alans);

        HSSFRow row28 = sheet.getRow(10);
        HSSFCell cell28 = row.createCell(25);
        cell.setCellValue(yuzey);

        HSSFRow row29 = sheet.getRow(11);
        HSSFCell cell29 = row.createCell(25);
        cell.setCellValue(isikci);

        HSSFRow row30 = sheet.getRow(12);
        HSSFCell cell30 = row.createCell(25);
        cell.setCellValue(kaldirma);

        HSSFRow row31 = sheet.getRow(19);
        HSSFCell cell31 = row.createCell(7);
        cell.setCellValue(stsapma);

        HSSFRow row32 = sheet.getRow(20);
        HSSFCell cell32 = row.createCell(7);
        cell.setCellValue(mdate);

        HSSFRow row33 = sheet.getRow(21);
        HSSFCell cell33 = row.createCell(7);
        cell.setCellValue(aciklama);

        HSSFRow row34 = sheet.getRow(24);
        HSSFCell cell34 = row.createCell(1);
        cell.setCellValue(parca1);

        HSSFRow row35 = sheet.getRow(24);
        HSSFCell cell35 = row.createCell(8);
        cell.setCellValue(kontrolu1);

        HSSFRow row36 = sheet.getRow(24);
        HSSFCell cell36 = row.createCell(11);
        cell.setCellValue(kaynaky);

        HSSFRow row37 = sheet.getRow(24);
        HSSFCell cell37 = row.createCell(17);
        cell.setCellValue(kalinlik);

        HSSFRow row38 = sheet.getRow(24);
        HSSFCell cell38 = row.createCell(18);
        cell.setCellValue(cap);

        HSSFRow row39 = sheet.getRow(24);
        HSSFCell cell39 = row.createCell(22);
        cell.setCellValue(hatatip);

        HSSFRow row40 = sheet.getRow(24);
        HSSFCell cell40 = row.createCell(24);
        cell.setCellValue(hatayeri);

        HSSFRow row41 = sheet.getRow(24);
        HSSFCell cell41 = row.createCell(27);
        cell.setCellValue(sonuc1);

        HSSFRow row42 = sheet.getRow(39);
        HSSFCell cell42 = row.createCell(5);
        cell.setCellValue(Op_Name);

        HSSFRow row43 = sheet.getRow(39);
        HSSFCell cell43 = row.createCell(15);
        cell.setCellValue(De_Name);

        HSSFRow row44 = sheet.getRow(39);
        HSSFCell cell44 = row.createCell(20);
        cell.setCellValue(On_Name);

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

