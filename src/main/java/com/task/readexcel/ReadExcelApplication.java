package com.task.readexcel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class ReadExcelApplication {

    ReadData readData;

    ReadExcelApplication(ReadData readData)
    {
        this.readData = readData;

        try {
            readData.readingExcelFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(ReadExcelApplication.class, args);
    }
}
