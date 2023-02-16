package com.yuge.ing.poi;

import com.yuge.ing.poi.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考：https://javacodepoint.com/how-to-write-data-to-an-existing-excel-file-in-java/
 *
 * @author: yuge
 * @date: 2023/2/16
 **/
@Slf4j
public class ModifyExcelTemplate {

    /**
     * Steps to update an Excel file using Apache POI
     * 1、Load an existing Excel file to InputStream. eg. FileInputStream inStream= new FileInputStream(new File(filePath));
     * 2、Get the Workbook from the InputStream. eg. Workbook workbook = WorkbookFactory.create(inStream);
     * 3、Update new data to an existing Sheet or create a new Sheet.
     * 4、Close the InputStream.
     * 5、Write the Workbook to an OutputStream. It will overwrite the existing file with updated data.
     * 6、Close the Workbook and OutputStream.
     *
     */
    public void modify() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("张三", "西安", "222@qq.com", 20));
        studentList.add(new Student("李四", "济南", "111@qq.com", 21));
        studentList.add(new Student("王五", "西双版纳", "333@qq.com", 22));

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        Workbook workbook = null;
        try {
            // Noting , Updating classes excel file
//            File xlsxFile = new File(path + "excel/2007.xlsx");
            File xlsxFile = new ClassPathResource("excel/2007.xlsx").getFile();
            log.info(xlsxFile.getPath());

            inputStream = new FileInputStream(xlsxFile);
            // The below code example will work for both Excel file formats XLS and XLSX because we used WorkbookFactory.
            // It creates HSSFWorkbook or XSSFWorkbook object based on the input file supplied.

            // Creating workbook from input stream
            workbook = WorkbookFactory.create(inputStream);

            // Reading first sheet of excel file
            Sheet sheet = workbook.getSheetAt(0);

            // Getting the count of existing records
            int rowCount = sheet.getLastRowNum();

            // Iterating new students to update
            for (Student student : studentList) {
                // Creating new row
                Row row = sheet.createRow(++rowCount);

                int columnCount = 0;

                // Creating new cell and setting the value
                // student name
                Cell cellStudentName = row.createCell(columnCount++);
                cellStudentName.setCellValue(student.getStudentName());
                // address
                Cell cellAddress = row.createCell(columnCount++);
                cellAddress.setCellValue(student.getAddress());
                // email
                Cell cellEmail = row.createCell(columnCount++);
                cellEmail.setCellValue(student.getEmail());
                // age
                Cell cellAge = row.createCell(columnCount++);
                cellAge.setCellValue(student.getAge());
            }

            // Creating output stream and writing the updated workbook
            outputStream = new FileOutputStream(xlsxFile);
            workbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close input stream
            IOUtils.closeQuietly(inputStream);
            // Close the workbook and output stream
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(outputStream);
        }
    }

    public static void main(String[] args) {
        ModifyExcelTemplate excel = new ModifyExcelTemplate();
        excel.modify();
    }

}
