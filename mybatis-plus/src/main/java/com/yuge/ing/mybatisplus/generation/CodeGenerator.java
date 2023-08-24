package com.yuge.ing.mybatisplus.generation;

public class CodeGenerator {

    public static void main(String[] args) {
        local();
    }

    private static void local() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://127.0.0.1:3306/ing_tutorial_mybatis_plus?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "",
                "");
        CodeGenerationUtil.generation(
                "com.yuge.ing.mybatisplus",
                info,
                "yuge",
                "D:\\Workspace\\GitHub-Workspace\\ing-tutorials\\mybatis-plus\\src\\main\\java",
                "D:\\Workspace\\GitHub-Workspace\\ing-tutorials\\mybatis-plus\\src\\main\\resources\\mappers",
                "",
                "character_set_demo");
    }

    private static void qt() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://172.16.110.67:3306/szxy_curriculum_arrangement?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "",
                "");
        CodeGenerationUtil.generation(
                "cn.qtone.ecampus.curriculum.arrangement",
                info,
                "",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\java",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\resources\\mappers",
                "cas_",
                "cas_schedule_task");
    }

    private static void sw() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://172.16.110.41:3306/szxy_sensitive_word?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "",
                "");
        CodeGenerationUtil.generation(
                "cn.qtone.ecampus.sensitive.word",
                info,
                "",
                "D:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\java",
                "D:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\resources\\mappers",
                "sw_",
                "sw_sensitive_word");
    }

    private static void qt_evaluation() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://172.16.110.67:3306/szxy_evaluation_manage?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "",
                "");
        CodeGenerationUtil.generation(
                "cn.qtone.ecampus.evaluation",
                info,
                "",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\java",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\resources\\mappers",
                "em_",
                "em_push");
    }

    private static void qt_course() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://172.16.110.67:3306/szxy_course_manage?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "course",
                "course$123");
        CodeGenerationUtil.generation(
                "cn.qtone.ecampus.course",
                info,
                "",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\java",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\resources\\mappers",
                "cm_",
                "cm_choose_plan_item");
    }

    private static void qt_survey() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://172.16.110.67:3306/szxy_wjmt?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "",
                "");
        CodeGenerationUtil.generation(
                "cn.qtone.ecampus.survey",
                info,
                "",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\java",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\resources\\mappers",
                "wj_",
                "wj_stat_form_item_answer", "wj_stat_form_item_count");
    }


}

