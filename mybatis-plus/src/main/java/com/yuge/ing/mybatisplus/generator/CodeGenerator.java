package com.yuge.ing.mybatisplus.generator;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Lists;
import com.yuge.ing.mybatisplus.generator.CodeGenerationUtil.GenerationParam;

import java.io.File;

/**
 * code generator
 *
 * @author yuge
 * @since 2023-05-12
 */
public class CodeGenerator {

    public static void main(String[] args) {
        cr();
    }


    private static void cr() {
        String projectPath = System.getProperty("user.dir");
        projectPath = "D:\\Program Files\\Qt-Workspace\\tmp2\\cr";
        // 修改为对应module、package
        String moduleName = "";
        String modulePackage = "";
        if (StringUtils.isNotBlank(moduleName)) {
            projectPath = projectPath.concat(File.separator).concat(moduleName);
        }
        projectPath = projectPath.concat(File.separator).concat("src")
                .concat(File.separator).concat("main")
                .concat(File.separator);
        String javaOutputDir = projectPath.concat("java");
        String xmlOutputDir = projectPath.concat("resources")
                .concat(File.separator).concat("mapper");
        if (StringUtils.isNotBlank(modulePackage)) {
            xmlOutputDir.concat(File.separator).concat(modulePackage);
        }
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://***:3306/***?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "***",
                "***");
        GenerationParam generationParam = new GenerationParam()
                .setRootPackage("cn.***")
                .setDataSourceInfo(info)
                .setAuthor("***")
                .setJavaOutputDir(javaOutputDir)
                .setXmlOutputDir(xmlOutputDir)
                .setPrefix("")
                .setTableNames(Lists.newArrayList("***"));
        CodeGenerationUtil.generation(generationParam);
    }


}

