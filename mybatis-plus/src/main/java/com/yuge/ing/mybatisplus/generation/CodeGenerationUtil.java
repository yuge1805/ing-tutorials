package com.yuge.ing.mybatisplus.generation;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.HashMap;
import java.util.Map;

public class CodeGenerationUtil {

    /**
     * 生成代码
     *
     * @param rootPackage       基础包名
     * @param dataSourceInfo    数据源信息
     * @param author            作者
     * @param javaOutputDir     代码输出路径
     * @param xmlOutputDir      mapper.xml输出路径
     * @param prefix            表前缀
     * @param tableNames        表名
     */
    public static void generation(String rootPackage,
                                  DataSourceInfo dataSourceInfo,
                                  String author,
                                  String javaOutputDir,
                                  String xmlOutputDir,
                                  String prefix,
                                  String... tableNames) {
        DataSourceConfig.Builder dataSourceBuild = new DataSourceConfig.Builder(dataSourceInfo.getUrl(), dataSourceInfo.getUserName(), dataSourceInfo.getPassword())
                .typeConvert(new CustomMySqlTypeConvert());
        FastAutoGenerator.create(dataSourceBuild)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .outputDir(javaOutputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    Map<OutputFile, String> outputFileStringMap = new HashMap<>();
                    outputFileStringMap.put(OutputFile.xml, xmlOutputDir);
                    builder.parent(rootPackage) // 设置父包名
                            .entity("entity") // entity包名
                            .pathInfo(outputFileStringMap); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
//                    builder.addTablePrefix(prefix); // 设置过滤表前缀
                    builder.addInclude(tableNames); // 设置需要生成的表名
                    builder.enableCapitalMode(); // 开启大写命名
                    // service
                    builder.serviceBuilder()
                            .formatServiceFileName("I%sService")
                            .formatServiceImplFileName("%sServiceImpl");
//                            .superServiceClass(IService.class)
//                            .superServiceImplClass(ServiceImpl.class);
                    // entity
                    builder.entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .addTableFills(new Column("createTime", FieldFill.INSERT),
                                    new Column("createUser", FieldFill.INSERT),
                                    new Column("updateTime", FieldFill.INSERT_UPDATE),
                                    new Column("updateUser", FieldFill.INSERT_UPDATE));
                    // 设置文件名
//                            .formatFileName("%s");
                    // mapper
                    builder.mapperBuilder()
//                            .superClass(BaseMapper.class)
                            .enableBaseResultMap()
                            .enableBaseColumnList();
                    // controller
                    builder.controllerBuilder()
                            .enableRestStyle();
                })
//                .injectionConfig(builder -> {
//                    Map<String, String> customFile = new HashMap<>();
//                    customFile.put("DTO.java", "/templates/dto.java.ftl");
//                    builder.customFile(customFile);
//                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
