package com.yuge.ing.neo4j.iddemo;

import com.yuge.ing.neo4j.constants.LineageConstants.NodeType;
import com.yuge.ing.neo4j.constants.LineageConstants.RelationshipType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Node(NodeType.DATABASE)
@Data
@Accessors(chain = true)
public class DatabaseNode extends BaseNode {

    /**
     * 数据库名称
     */
    private String name;

    /**
     * 数据源
     */
    @Relationship(type = RelationshipType.BELONG_TO, direction = Direction.OUTGOING)
    private DataSourceNode dataSource;

}
