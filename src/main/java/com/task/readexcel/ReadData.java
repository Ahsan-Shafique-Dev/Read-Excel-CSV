package com.task.readexcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    ArrayList<ModelClass> list = new ArrayList<>();

    public void addRecord(ModelClass modelClass) {
        String querry = "INSERT INTO excel (id, names, department) VALUES (?,?,?)";
        jdbcTemplate.update(querry, modelClass.getSrNo(), modelClass.getName(), modelClass.getDepartment());
    }

    public void readingExcelFile() {
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\DELL\\Desktop\\Record.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            int rowNum = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (rowNum == 0) {
                    rowNum++;
                    continue;
                }
                int ids = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String departments = row.getCell(2).getStringCellValue();
                list.add(new ModelClass(ids, name, departments));
            }
            list.forEach((r) -> {
                addRecord(r);
            });
            file.close();
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
        Scanner sc = new Scanner(new File("C:\\Users\\DELL\\Downloads\\dummy-data-2.csv"));
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            System.out.println(sc.next());
//            System.out.println("");//find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner
    }

}