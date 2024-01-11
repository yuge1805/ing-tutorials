package com.yuge.ing.sqlparser.gsqlparser;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.dlineage.DataFlowAnalyzer;
import gudusoft.gsqlparser.dlineage.dataflow.model.xml.dataflow;
import org.junit.jupiter.api.Test;

/**
 * @author: yuge
 * @date: 2024/1/10
 **/
public class GSqlParserTemplate {

    public static void main(String[] args) {
    }

    public static String analyze(String sql) {
        DataFlowAnalyzer analyzer = new DataFlowAnalyzer(sql, EDbVendor.dbvmysql, false);
        analyzer.setShowJoin(false);
        analyzer.setIgnoreTemporaryTable(false);
        // 忽略中间rs
        analyzer.setIgnoreRecordSet(true);
        // 忽略坐标
        analyzer.setIgnoreCoordinate(true);
        // 显示count字段
        analyzer.setShowCountTableColumn(true);
        // text输出
        analyzer.setTextFormat(true);
        String xml = analyzer.generateDataFlow();
        dataflow dataFlow = analyzer.getDataFlow();
        System.out.println(dataFlow);
        return xml;
    }

    @Test
    public void testSql() {
        String sql = "SELECT\n" +
                "    cpi.courseId,\n" +
                "    c.centerCourseId,\n" +
                "    cpi.courseName,\n" +
                "    cpi.gradeIdArray,\n" +
                "    IFNULL(courseStudent.chooseStudentNum, 0) AS chooseStudentNum\n" +
                "FROM\n" +
                "    c_item cpi\n" +
                "    LEFT JOIN c_course c ON cpi.courseId = c.id AND c.delStatus = 0\n" +
                "    LEFT JOIN (\n" +
                "    SELECT\n" +
                "        cr.courseId,\n" +
                "        count( cr.studentSequence ) AS chooseStudentNum\n" +
                "    FROM\n" +
                "        c_resut cr\n" +
                "    WHERE\n" +
                "        cr.delStatus = 0\n" +
                "    GROUP BY\n" +
                "        cr.courseId\n" +
                "    ) AS courseStudent ON cpi.courseId = courseStudent.courseId\n" +
                "WHERE\n" +
                "    cpi.delStatus = 0";
        String xml = analyze(sql);
        System.out.println(xml);
    }

    @Test
    public void testSqlA() {
        String sql = "SELECT\n" +
                "    cpi.courseId,\n" +
                "    c.centerCourseId,\n" +
                "    cpi.courseName,\n" +
                "    cpi.gradeIdArray,\n" +
                "    courseStudent.chooseStudentNum\n" +
                "FROM\n" +
                "    c_item cpi\n" +
                "    LEFT JOIN c_course c ON cpi.courseId = c.id AND c.delStatus = 0\n" +
                "    LEFT JOIN (\n" +
                "    SELECT\n" +
                "        cr.courseId,\n" +
                "        count( cr.studentSequence ) AS chooseStudentNum\n" +
                "    FROM\n" +
                "        c_resut cr\n" +
                "    WHERE\n" +
                "        cr.delStatus = 0\n" +
                "    GROUP BY\n" +
                "        cr.courseId\n" +
                "    ) AS courseStudent ON cpi.courseId = courseStudent.courseId\n" +
                "WHERE\n" +
                "    cpi.delStatus = 0";
        String xml = analyze(sql);
        System.out.println(xml);
    }

}
