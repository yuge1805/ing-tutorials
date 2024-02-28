package com.yuge.ing.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_BUSINESS_SERVICE_IMPL_IMPORT_PACKAGES;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CLASS_BUSINESS_SERVICE;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CLASS_BUSINESS_SERVICE_IMPL;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CLASS_CONDITION;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CLASS_CONVERTER;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CLASS_DTO;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CLASS_ENTITY;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CLASS_PAGE_QUERY;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CLASS_VO;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_CONTROLLER_IMPORT_PACKAGES;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_PACKAGE;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_PACKAGE_BUSINESS_SERVICE;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_PACKAGE_BUSINESS_SERVICE_IMPL;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_PACKAGE_CONDITION;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_PACKAGE_CONVERTER;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_PACKAGE_DTO;
import static com.yuge.ing.mybatisplus.generator.TemplateConstants.VAR_PACKAGE_VO;

/**
 * code generator
 *
 * @author yuge
 * @since 2023-05-12
 */
public class CodeGenerationUtil {

    private final static String DTO_SUFFIX = "DTO";
    private final static String VO_SUFFIX = "VO";
    private final static String CONDITION_SUFFIX = "Condition";
    private final static String PAGE_QUERY_SUFFIX = "PageQuery";
    private final static String CONVERTER_SUFFIX = "Converter";
    private final static String BUSINESS_SERVICE_SUFFIX = "BusinessService";
    private final static String BUSINESS_SERVICE_IMPL_SUFFIX = "BusinessServiceImpl";


    private final static String DTO_PACKAGE_NAME = "dto";
    private final static String VO_PACKAGE_NAME = "vo";
    private final static String CONDITION_PACKAGE_NAME = "condition";
    private final static String CONVERTER_PACKAGE_NAME = "converter";
    private final static String BUSINESS_SERVICE_PACKAGE_NAME = "business";
    private final static String BUSINESS_SERVICE_IMPL_PACKAGE_NAME = "business.impl";

    /**
     * 生成代码
     *
     * @param generationParam
     */
    public static void generation(GenerationParam generationParam) {
        String rootPackage = generationParam.getRootPackage();
        DataSourceInfo dataSourceInfo = generationParam.getDataSourceInfo();
        DataSourceConfig.Builder dataSourceBuild = new DataSourceConfig.Builder(dataSourceInfo.getUrl(), dataSourceInfo.getUserName(), dataSourceInfo.getPassword())
                .typeConvertHandler(new CustomMySqlTypeConvertHandler());
        FastAutoGenerator.create(dataSourceBuild)
                .globalConfig(builder -> {
                    builder.author(generationParam.getAuthor()) // 设置作者
                            .disableOpenDir() // 禁止打开目录
                            .outputDir(generationParam.getJavaOutputDir()); // 指定输出目录
                })
                .packageConfig(builder -> {
                    Map<OutputFile, String> outputFileStringMap = new HashMap<>();
                    outputFileStringMap.put(OutputFile.xml, generationParam.getXmlOutputDir());
                    builder.parent(generationParam.getRootPackage()) // 设置父包名
                            .entity("entity") // entity包名
                            .pathInfo(outputFileStringMap); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addTablePrefix(generationParam.getPrefix()); // 设置过滤表前缀
                    builder.addInclude(generationParam.getTableNames()); // 设置需要生成的表名
                    builder.enableCapitalMode(); // 开启大写命名
                    // service
                    builder.serviceBuilder()
                            .formatServiceFileName("I%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .superServiceClass(generationParam.getSuperServiceClassName())
                            .superServiceImplClass(generationParam.getSuperServiceImplClassName());
                    // entity
                    builder.entityBuilder()
                            .enableLombok()
                            .disableSerialVersionUID()
                            .enableTableFieldAnnotation()
                            .addTableFills(generationParam.getTableFillList());
                    // 设置文件名
//                            .formatFileName("%s");
                    // mapper
                    builder.mapperBuilder()
                            .superClass(generationParam.getSuperMapperClassName())
                            .enableBaseResultMap()
                            .enableBaseColumnList();
                    // controller
                    builder.controllerBuilder()
                            .superClass(generationParam.getSuperControllerClassName())
                            .enableRestStyle();
                })
                .injectionConfig(builder -> {
                    builder.beforeOutputFile((tableInfo, stringObjectMap) -> {
                        String entity = (String) stringObjectMap.getOrDefault(VAR_CLASS_ENTITY, "");
                        stringObjectMap.put(VAR_CLASS_DTO, entity.concat(DTO_SUFFIX));
                        stringObjectMap.put(VAR_CLASS_VO, entity.concat(VO_SUFFIX));
                        stringObjectMap.put(VAR_CLASS_CONDITION, entity.concat(CONDITION_SUFFIX));
                        stringObjectMap.put(VAR_CLASS_PAGE_QUERY, entity.concat(PAGE_QUERY_SUFFIX));
                        stringObjectMap.put(VAR_CLASS_CONVERTER, entity.concat(CONVERTER_SUFFIX));
                        stringObjectMap.put(VAR_CLASS_BUSINESS_SERVICE, entity.concat(BUSINESS_SERVICE_SUFFIX));
                        stringObjectMap.put(VAR_CLASS_BUSINESS_SERVICE_IMPL, entity.concat(BUSINESS_SERVICE_IMPL_SUFFIX));

                        Map<String, String> packageMap = (Map<String, String>) stringObjectMap.getOrDefault(VAR_PACKAGE, Maps.newHashMap());
                        packageMap = Maps.newHashMap(packageMap);
                        packageMap.put(VAR_PACKAGE_DTO, rootPackage.concat(".").concat(DTO_PACKAGE_NAME));
                        packageMap.put(VAR_PACKAGE_VO, rootPackage.concat(".").concat(VO_PACKAGE_NAME));
                        packageMap.put(VAR_PACKAGE_CONDITION, rootPackage.concat(".").concat(CONDITION_PACKAGE_NAME));
                        packageMap.put(VAR_PACKAGE_CONVERTER, rootPackage.concat(".").concat(CONVERTER_PACKAGE_NAME));
                        packageMap.put(VAR_PACKAGE_BUSINESS_SERVICE, rootPackage.concat(".").concat(BUSINESS_SERVICE_PACKAGE_NAME));
                        packageMap.put(VAR_PACKAGE_BUSINESS_SERVICE_IMPL, rootPackage.concat(".").concat(BUSINESS_SERVICE_IMPL_PACKAGE_NAME));
                        stringObjectMap.put(VAR_PACKAGE, packageMap);
                    });
                    Map<String, Object> customMap = Maps.newHashMap();
                    builder.customMap(customMap);
                    customMap.put("package_PageQuery", "com.yuge.ing.mybatisplus.common.api.PageQuery");
                    customMap.put(VAR_BUSINESS_SERVICE_IMPL_IMPORT_PACKAGES,
                            Lists.newArrayList("com.yuge.ing.mybatisplus.common.api.ResultCode",
                                    "com.yuge.ing.mybatisplus.common.exception.ApiException"));
                    customMap.put(VAR_CONTROLLER_IMPORT_PACKAGES,
                            Lists.newArrayList("com.yuge.ing.mybatisplus.common.api.CommonPage",
                                    "com.yuge.ing.mybatisplus.common.api.CommonResult"));
                    builder.customFile(new CustomFile.Builder()
                            .fileName(DTO_SUFFIX.concat(".java"))
                            .templatePath("/templates/dto.java.ftl")
                            .packageName(DTO_PACKAGE_NAME)
                            .build());
                    // pageQuery condition
                    builder.customFile(new CustomFile.Builder()
                            .fileName(PAGE_QUERY_SUFFIX.concat(".java"))
                            .templatePath("/templates/query.java.ftl")
                            .packageName(DTO_PACKAGE_NAME)
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName(CONDITION_SUFFIX.concat(".java"))
                            .templatePath("/templates/condition.java.ftl")
                            .packageName(CONDITION_PACKAGE_NAME)
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName(VO_SUFFIX.concat(".java"))
                            .templatePath("/templates/vo.java.ftl")
                            .packageName(VO_PACKAGE_NAME)
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName(CONVERTER_SUFFIX.concat(".java"))
                            .templatePath("/templates/converter.java.ftl")
                            .packageName(CONVERTER_PACKAGE_NAME)
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName(BUSINESS_SERVICE_SUFFIX.concat(".java"))
                            .templatePath("/templates/businessService.java.ftl")
                            .packageName(BUSINESS_SERVICE_PACKAGE_NAME)
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName(BUSINESS_SERVICE_IMPL_SUFFIX.concat(".java"))
                            .templatePath("/templates/businessServiceImpl.java.ftl")
                            .packageName(BUSINESS_SERVICE_IMPL_PACKAGE_NAME)
                            .build());
                })
                .templateEngine(new OwnerFreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class GenerationParam {

        /**
         * 基础包名
         */
        private String rootPackage;

        /**
         * 数据源信息
         */
        private DataSourceInfo dataSourceInfo;

        /**
         * 作者
         */
        private String author;

        /**
         * 代码输出路径
         */
        private String javaOutputDir;

        /**
         * mapper.xml输出路径
         */
        private String xmlOutputDir;

        /**
         * 表前缀
         */
        private String prefix;

        /**
         * 表名
         */
        private List<String> tableNames;

        private String superServiceClassName = "com.baomidou.mybatisplus.extension.service.IService";

        private String superServiceImplClassName = "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl";

        private String superMapperClassName = "com.baomidou.mybatisplus.core.mapper.BaseMapper";

        private String superControllerClassName = null;

        private List<IFill> tableFillList = Lists.newArrayList(
                new Column("createUser", FieldFill.INSERT),
                new Column("createTime", FieldFill.INSERT),
                new Column("updateUser", FieldFill.INSERT_UPDATE),
                new Column("updateTime", FieldFill.INSERT_UPDATE));

    }

}
