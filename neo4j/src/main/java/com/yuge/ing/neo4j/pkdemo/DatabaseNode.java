package com.yuge.ing.neo4j.pkdemo;

import com.yuge.ing.neo4j.constants.LineageConstants.NodeType;
import com.yuge.ing.neo4j.constants.LineageConstants.RelationshipType;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Node(NodeType.DATABASE + "pk")
@Data
public class DatabaseNode extends BaseNode {

    private Long id;

    /**
     * 数据库名称
     */
    private String name;

    /**
     * 数据源
     */
    @Relationship(type = RelationshipType.BELONG_TO, direction = Direction.OUTGOING)
    private DataSourceNode dataSource;

    public DatabaseNode(String name, DataSourceNode dataSource) {
        this.name = name;
        this.dataSource = dataSource;
        this.pk = dataSource.getName() + "/" + name;
    }
}
