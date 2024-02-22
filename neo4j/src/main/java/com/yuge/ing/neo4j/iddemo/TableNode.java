package com.yuge.ing.neo4j.iddemo;

import com.yuge.ing.neo4j.constants.LineageConstants.NodeType;
import com.yuge.ing.neo4j.constants.LineageConstants.RelationshipType;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.List;

/**
 * @author: yuge
 * @date: 2024/2/5
 **/
@Node(NodeType.TABLE)
@Data
public class TableNode extends BaseNode {

    /**
     * table name
     */
    private String name;

    /**
     * 数据库
     */
    @Relationship(type = RelationshipType.BELONG_TO, direction = Direction.OUTGOING)
    private DatabaseNode database;

    /**
     * 目标表
     */
    @Relationship(type = RelationshipType.BELONG_TO, direction = Direction.OUTGOING)
    private List<TableNode> targetTables;

    /**
     * 目标表
     */
    @Relationship(type = RelationshipType.BELONG_TO, direction = Direction.INCOMING)
    private List<TableNode> sourceTables;

    /**
     * 关系映射
     */
    @Relationship(type = RelationshipType.DATA_FLOW, direction = Direction.OUTGOING)
    private List<TableRelationship> targetRelationships;

    /**
     * 关系映射
     */
    @Relationship(type = RelationshipType.DATA_FLOW, direction = Direction.INCOMING)
    private List<TableRelationship> sourceRelationships;

}
