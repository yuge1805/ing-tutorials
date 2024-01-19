package com.yuge.ing.neo4j.constants;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
public class LineageConstants {

    /**
     * 节点类型
     */
    public static class NodeType {

        public static final String DATASOURCE = "DataSource";

        public static final String DATABASE = "Database";

        public static final String TABLE = "Table";

        public static final String COLUMN = "Column";

    }

    /**
     * 关系类型
     */
    public static class RelationshipType {

        public static final String BELONG_TO = "BELONG_TO";

    }


}
