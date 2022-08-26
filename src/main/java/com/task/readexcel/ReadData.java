package com.task.readexcel;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class ReadData {

    JdbcTemplate jdbcTemplate;

    @Autowired
    ReadData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ReadData() {
    }

    ModelClass modelClass = new ModelClass();
    ArrayList<ModelClass> excelList = new ArrayList<>();
    ArrayList<CSV_ModelClass> csvList = new ArrayList<CSV_ModelClass>();

    public void addExcelRecordToDb(ModelClass modelClass) {
        String querry = "INSERT INTO excel (id, names, department) VALUES (?,?,?) ON CONFLICT ON CONSTRAINT excel_id_key DO NOTHING";
        jdbcTemplate.update(querry, modelClass.getSrNo(), modelClass.getName(), modelClass.getDepartment());
    }

    public void addCsvRecordToDb(CSV_ModelClass csv_modelClass) {

        String querry = "INSERT INTO csv (type, sku, name, is_published, is_featured, visibility) VALUES (?,?,?,?,?,?) ON CONFLICT ON CONSTRAINT csv_sku_key DO NOTHING";
        jdbcTemplate.update(querry,
                csv_modelClass.getType(),
                csv_modelClass.getSku(),
                csv_modelClass.getName(),
                csv_modelClass.getIsPulished(),
                csv_modelClass.getIsFeatured(),
                csv_modelClass.getVisibility());
    }

    public void readingExcelFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:/Users/DELL/Desktop/Record.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = 0;
            while (sheet.iterator().hasNext()) {
                Row row = sheet.iterator().next();
                if (rowNum != 0) {
                    addExcelRecordToDb(new ModelClass((int) row.getCell(0).getNumericCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue()));
                }
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writingExcelFile() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
//        data.put("1", new Object[]{"ID", "NAME", "Department"});
        data.put("2", new Object[]{1, "Ahsan", "Dev Tech"});
        data.put("3", new Object[]{2, "Sameer", "IT"});
        data.put("4", new Object[]{3, "Asif", "Application "});
        data.put("5", new Object[]{4, "Shahaque", "Backend"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("records.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("record.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readingCsvFile() throws FileNotFoundException {
        try {
            FileReader filereader = new FileReader("C:\\Users\\DELL\\Downloads\\dummy-data-2.csv");

            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
//            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                String type = nextRecord[0];
                String sku = nextRecord[1];
                String name = nextRecord[2];
                String isPublished = nextRecord[3];
                String isFeatured = nextRecord[4];
                String visibility = nextRecord[5];

                csvList.add(new CSV_ModelClass(type, sku, name, isPublished, isFeatured, visibility));
            }
            csvList.forEach((r) -> addCsvRecordToDb(r));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void csvFileRead() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\DELL\\Downloads\\dummy-data-2.csv"));

        sc.useDelimiter(",");

        while (sc.hasNext()) {
            int rowNum = 0;

            System.out.println(sc.next());
        }
        sc.close();
    }
}