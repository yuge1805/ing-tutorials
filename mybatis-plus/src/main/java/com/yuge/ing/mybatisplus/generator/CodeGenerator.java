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
//        sdty();
//        cr();
//        portal_website();
        basedata();
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
                "jdbc:mysql://192.168.1.64:3306/szxy_curriculum_arrangement?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "szxy",
                "Qtone*2023");
        GenerationParam generationParam = new GenerationParam()
                .setRootPackage("cn.qtone.ecampus.curriculum.arrangement")
                .setDataSourceInfo(info)
                .setAuthor("zhangbw")
                .setJavaOutputDir(javaOutputDir)
                .setXmlOutputDir(xmlOutputDir)
                .setPrefix("")
                .setTableNames(Lists.newArrayList("cas_push_record"));
        CodeGenerationUtil.generation(generationParam);
    }

    private static void portal_website() {
        String projectPath = System.getProperty("user.dir");
        projectPath = "D:\\Program Files\\Qt-Workspace\\tmp2\\portal";
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
                "jdbc:mysql://192.168.1.64:3306/szxydb?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "szxy",
                "Qtone*2023");
        GenerationParam generationParam = new GenerationParam()
                .setRootPackage("cn.qtone.ecampus.portal")
                .setDataSourceInfo(info)
                .setAuthor("zhangbw")
                .setJavaOutputDir(javaOutputDir)
                .setXmlOutputDir(xmlOutputDir)
                .setPrefix("")
                .setTableNames(Lists.newArrayList("mh_digital_government_third_user_apply"));
        CodeGenerationUtil.generation(generationParam);
    }

    private static void basedata() {
        String projectPath = System.getProperty("user.dir");
        projectPath = "D:\\Program Files\\Qt-Workspace\\tmp2\\basedata";
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
                "jdbc:mysql://192.168.1.64:3306/szxydb?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "szxy",
                "Qtone*2023");
        GenerationParam generationParam = new GenerationParam()
                .setRootPackage("cn.qtone.ecampus.basedata")
                .setDataSourceInfo(info)
                .setAuthor("zhangbw")
                .setJavaOutputDir(javaOutputDir)
                .setXmlOutputDir(xmlOutputDir)
                .setPrefix("")
                .setTableNames(Lists.newArrayList("uc_jyeoo_teacher"));
        CodeGenerationUtil.generation(generationParam);
    }

    private static void shardingsphere() {
        String projectPath = System.getProperty("user.dir");
        projectPath = "D:\\Workspace\\GitHub-Workspace\\ing-tutorials\\shardingsphere";
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
                "jdbc:mysql://127.0.0.1:3306/ds?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "root",
                "root");
        GenerationParam generationParam = new GenerationParam()
                .setRootPackage("com.yuge.ing.shardingsphere")
                .setDataSourceInfo(info)
                .setAuthor("yuge")
                .setJavaOutputDir(javaOutputDir)
                .setXmlOutputDir(xmlOutputDir)
                .setPrefix("t_")
                .setTableNames(Lists.newArrayList("t_single_table"));
        CodeGenerationUtil.generation(generationParam);
    }

    private static void sdty() {
        String projectPath = System.getProperty("user.dir");
        projectPath = "D:\\Program Files\\Qt-Workspace\\tmp2\\sdty";
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
                "jdbc:mysql://172.16.110.129:3306/gym?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "gym",
                "gym123");
        GenerationParam generationParam = new GenerationParam()
                .setRootPackage("cn.qtone.gym.basedata")
                .setDataSourceInfo(info)
                .setAuthor("zhangbw")
                .setJavaOutputDir(javaOutputDir)
                .setXmlOutputDir(xmlOutputDir)
                .setPrefix("")
                .setTableNames(Lists.newArrayList("gym_bd_account_app"));
        CodeGenerationUtil.generation(generationParam);
    }


}

