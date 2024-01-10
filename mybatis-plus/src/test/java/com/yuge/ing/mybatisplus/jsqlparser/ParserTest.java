package com.yuge.ing.mybatisplus.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: yuge
 * @date: 2023/9/18
 **/
public class ParserTest {

    @Test
    public void testMysql() throws JSQLParserException {
        String sql = "CREATE TABLE `dc_table_version` (\n" +
                "  `id` bigint NOT NULL AUTO_INCREMENT,\n" +
                "  `table_id` bigint NOT NULL COMMENT '数据库id',\n" +
                "  `version` varchar(255) NOT NULL COMMENT '版本号',\n" +
                "  `extension` json DEFAULT NULL COMMENT '扩展参数',\n" +
                "  `create_script` text COMMENT '建表脚本',\n" +
                "  `source_type` smallint NOT NULL DEFAULT '0' COMMENT '数据来源；0 自动创建；1 手动创建',\n" +
                "  `is_current` smallint DEFAULT '0' COMMENT '是否当前；0 否；1 是',\n" +
                "  `del_status` smallint NOT NULL DEFAULT '0' COMMENT '删除状态，0：未删除，1：已删除',\n" +
                "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `create_user_id` bigint DEFAULT NULL COMMENT '创建人id',\n" +
                "  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人',\n" +
                "  `update_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  `update_user_id` bigint DEFAULT NULL COMMENT '修改人id',\n" +
                "  `update_user_name` varchar(255) DEFAULT NULL COMMENT '修改人',\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  UNIQUE KEY `uk_table_id_version` (`table_id`,`version`) USING BTREE,\n" +
                "  KEY `idx_table_id` (`table_id`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='表版本';";
        Statement statement = CCJSqlParserUtil.parse(sql);
        if (statement instanceof CreateTable) {
            CreateTable createTable = (CreateTable) statement;
            for (ColumnDefinition columnDefinition : createTable.getColumnDefinitions()) {
                System.out.println("column");
                System.out.println(columnDefinition);
            }
            for (Index index : createTable.getIndexes()) {
                System.out.println("index");
                System.out.println(index);
            }
        }
    }

    @Test
    public void testDoris() throws JSQLParserException {
        String sql = "CREATE TABLE `expamle_aggregate_tbl` (\n" +
                "  `user_id` largeint(40) NOT NULL COMMENT '用户id',\n" +
                "  `date` date NOT NULL COMMENT '数据灌入日期时间',\n" +
                "  `timestamp` datetime NOT NULL COMMENT '数据灌入的时间戳',\n" +
                "  `city` varchar(20) NULL COMMENT '用户所在城市',\n" +
                "  `age` smallint(6) NULL COMMENT '用户年龄',\n" +
                "  `sex` tinyint(4) NULL COMMENT '用户性别',\n" +
                "  `last_visit_date` datetime REPLACE NULL DEFAULT \"1970-01-01 00:00:00\" COMMENT '用户最后一次访问时间',\n" +
                "  `cost` bigint(20) SUM NULL DEFAULT \"0\" COMMENT '用户总消费',\n" +
                "  `max_dwell_time` int(11) MAX NULL DEFAULT \"0\" COMMENT '用户最大停留时间',\n" +
                "  `min_dwell_time` int(11) MIN NULL DEFAULT \"99999\" COMMENT '用户最小停留时间'\n" +
                ") ENGINE=OLAP\n" +
                "AGGREGATE KEY(`user_id`, `date`, `timestamp`, `city`, `age`, `sex`)\n" +
                "COMMENT 'OLAP'\n" +
                "PARTITION BY RANGE(`date`)\n" +
                "(PARTITION p201701 VALUES [('0000-01-01'), ('2017-02-01')),\n" +
                "PARTITION p201702 VALUES [('2017-02-01'), ('2017-03-01')),\n" +
                "PARTITION p201703 VALUES [('2017-03-01'), ('2017-04-01')))\n" +
                "DISTRIBUTED BY HASH(`user_id`) BUCKETS 16\n" +
                "PROPERTIES (\n" +
                "\"replication_allocation\" = \"tag.location.default: 3\",\n" +
                "\"in_memory\" = \"false\",\n" +
                "\"storage_format\" = \"V2\",\n" +
                "\"disable_auto_compaction\" = \"false\"\n" +
                ");";
//        Statement statement = CCJSqlParserUtil.parse(sql);
//        if (statement instanceof CreateTable) {
//            CreateTable createTable = (CreateTable) statement;
//            for (ColumnDefinition columnDefinition : createTable.getColumnDefinitions()) {
//                System.out.println("column");
//                System.out.println(columnDefinition);
//            }
//            for (Index index : createTable.getIndexes()) {
//                System.out.println("index");
//                System.out.println(index);
//            }
//        }
// 正则表达式模式
        String partitionPattern = "PARTITION BY ([^;]+)";
        String partitionValuePattern = "PARTITION ([^ ]+) VALUES \\[([^\\]]+)\\]";

        // 创建 Pattern 对象
        Pattern partitionPatternMatcher = Pattern.compile(partitionPattern, Pattern.DOTALL);
        Pattern partitionValuePatternMatcher = Pattern.compile(partitionValuePattern);

        // 创建 Matcher 对象
        Matcher partitionMatcher = partitionPatternMatcher.matcher(sql);

        // 查找分区信息
        if (partitionMatcher.find()) {
            String partitionInfo = partitionMatcher.group(1).trim();
            System.out.println("Partition Info: " + partitionInfo);

            System.out.println("=============================================");
            // 查找分区字段和分区值
            Matcher partitionValueMatcher = partitionValuePatternMatcher.matcher(partitionInfo);
            while (partitionValueMatcher.find()) {
                String partitionField = partitionValueMatcher.group(1).trim();
                String partitionValues = partitionValueMatcher.group(2).trim();
                System.out.println("Partition Field: " + partitionField);
                System.out.println("Partition Values: " + partitionValues);
            }
        }
    }




}
