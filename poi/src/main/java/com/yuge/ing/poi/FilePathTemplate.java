package com.yuge.ing.poi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @author: yuge
 * @date: 2023/2/16
 **/
@Slf4j
public class FilePathTemplate {

    public static void main(String[] args) throws IOException {
        // 获取当前项目路径
        String currentProjectPath = System.getProperty("user.dir");
        log.info("project path: {}", currentProjectPath);

        // 获取classes路径
        URL resource = FilePathTemplate.class.getClassLoader().getResource("");
        String path = resource.getPath();
        // 空格被转义为%20，需解码
        path = URLDecoder.decode(path);
        path = path.substring(1);
        log.info("classes path: {}", path);

        // classes file
        File xlsxFile = new File(path + "excel/2007.xlsx");
        log.info("path: {}", xlsxFile.getPath());

        // Spring 方式获取资源
        File classPathXlsxFile = new ClassPathResource("excel/2007.xlsx").getFile();
        log.info("path2: {}", classPathXlsxFile.getPath());

    }

}
