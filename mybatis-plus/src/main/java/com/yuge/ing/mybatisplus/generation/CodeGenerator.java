package com.yuge.ing.mybatisplus.generation;

public class CodeGenerator {

    public static void main(String[] args) {
        qt();
    }

    private static void local() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://127.0.0.1:3306/ing_tutorial_mybatis_plus?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "root",
                "root");
        CodeGenerationUtil.generation(
                "com.yuge.ing.mybatisplus",
                info,
                "yuge",
                "E:\\Program Files\\tmp\\src\\main\\java",
                "E:\\Program Files\\tmp\\src\\main\\resources\\mappers",
                "base_",
                "base_org");
    }

    private static void qt() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://172.16.110.67:3306/szxy_curriculum_arrangement?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "curriculum",
                "curriculum$123");
        CodeGenerationUtil.generation(
                "cn.qtone.ecampus.curriculum.arrangement",
                info,
                "zhangbw",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\java",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\resources\\mappers",
                "cas_",
                "cas_schedule_task");
    }

    private static void qt_evaluation() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://172.16.110.67:3306/szxy_evaluation_manage?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "evaluation",
                "evaluation$123");
        CodeGenerationUtil.generation(
                "cn.qtone.ecampus.evaluation",
                info,
                "zhangbw",
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
                "zhangbw",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\java",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\resources\\mappers",
                "cm_",
                "cm_choose_plan_item");
    }

    private static void qt_survey() {
        DataSourceInfo info = new DataSourceInfo(
                "jdbc:mysql://172.16.110.67:3306/szxy_wjmt?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                "wjmt",
                "wjmt$123");
        CodeGenerationUtil.generation(
                "cn.qtone.ecampus.survey",
                info,
                "zhangbw",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\java",
                "E:\\Program Files\\Qt-Workspace\\tmp\\src\\main\\resources\\mappers",
                "wj_",
                "wj_stat_form_item_answer", "wj_stat_form_item_count");
    }


}

