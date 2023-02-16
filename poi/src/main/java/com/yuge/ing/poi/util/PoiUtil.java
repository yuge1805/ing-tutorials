package com.yuge.ing.poi.util;

import com.yuge.ing.poi.entity.Student;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
import java.util.List;
import java.util.Objects;

/**
 * @author: yuge
 * @date: 2023/2/16
 **/
@Slf4j
public class PoiUtil {

    /**
     * 修改excel
     *
     * @param file
     * @param cellDataList
     */
    public static void modify(File file, List<CellData> cellDataList) {
        if (CollectionUtils.isEmpty(cellDataList)) {
            return;
        }
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(file);

            // Creating workbook from input stream
            workbook = WorkbookFactory.create(inputStream);

            // Reading first sheet of excel file
            Sheet sheet = workbook.getSheetAt(0);

            for (CellData cellData : cellDataList) {
                if (Objects.isNull(cellData)) {
                    continue;
                }
                Row row = sheet.getRow(cellData.getRowIndex());
                if (Objects.isNull(row)) {
                    row = sheet.createRow(cellData.getRowIndex());
                }

                Cell cell = row.getCell(cellData.getColumnIndex());
                if (Objects.isNull(cell)) {
                    cell = row.createCell(cellData.getColumnIndex());
                }

                cell.setCellValue(cellData.getValue());
            }

            // Creating output stream and writing the updated workbook
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);

        } catch (IOException e) {
            log.error("modify error!", e);
        } finally {
            // Close input stream
            IOUtils.closeQuietly(inputStream);
            // Close the workbook and output stream
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(outputStream);
        }
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CellData {

        private int rowIndex;

        private int columnIndex;

        private String value;

    }

}
