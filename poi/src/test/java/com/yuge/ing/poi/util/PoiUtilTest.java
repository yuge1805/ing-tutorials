package com.yuge.ing.poi.util;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PoiUtilTest {

    @Test
    void modify() throws IOException {
        File xlsxFile = new ClassPathResource("excel/2007.xlsx").getFile();
        List<PoiUtil.CellData> dataList = new ArrayList<>();
        dataList.add(new PoiUtil.CellData(20, 5, "sss"));
        dataList.add(new PoiUtil.CellData(21, 5, "aaa"));
        dataList.add(new PoiUtil.CellData(22, 5, "bbb"));
        PoiUtil.modify(xlsxFile, dataList);
    }
}