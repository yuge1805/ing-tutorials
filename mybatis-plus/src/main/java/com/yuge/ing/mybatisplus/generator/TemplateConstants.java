package com.yuge.ing.mybatisplus.generator;

/**
 * 模板常量
 *
 * @author: yuge
 * @since 2023/5/12
 **/
public class TemplateConstants {

    /**
     * 包名变量集合
     */
    public static String VAR_PACKAGE = "package";
    /**
     * 模板中DTO包名变量： ${package.DTO}
     */
    public static String VAR_PACKAGE_DTO = "DTO";
    public static String VAR_PACKAGE_VO = "VO";
    public static String VAR_PACKAGE_CONDITION = "Condition";
    public static String VAR_PACKAGE_CONVERTER = "Converter";
    public static String VAR_PACKAGE_BUSINESS_SERVICE = "BusinessService";
    public static String VAR_PACKAGE_BUSINESS_SERVICE_IMPL = "BusinessServiceImpl";

    /**
     * 模板中DTO类名变量：${dto}
     */
    public static String VAR_CLASS_ENTITY = "entity";
    public static String VAR_CLASS_DTO = "dto";
    public static String VAR_CLASS_VO = "vo";
    public static String VAR_CLASS_CONDITION = "condition";
    public static String VAR_CLASS_CONVERTER = "converter";
    public static String VAR_CLASS_PAGE_QUERY = "pageQuery";
    public static String VAR_CLASS_BUSINESS_SERVICE = "businessService";
    public static String VAR_CLASS_BUSINESS_SERVICE_IMPL = "businessServiceImpl";

    /**
     * 模板中businessServiceImpl引入的包列表
     */
    public static String VAR_BUSINESS_SERVICE_IMPL_IMPORT_PACKAGES = "businessServiceImplImportPackages";

    /**
     * 模板中controller引入的包列表
     */
    public static String VAR_CONTROLLER_IMPORT_PACKAGES = "controllerImportPackages";

}
