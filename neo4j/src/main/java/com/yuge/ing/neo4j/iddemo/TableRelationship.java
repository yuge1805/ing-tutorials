package com.yuge.ing.neo4j.iddemo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * @author: yuge
 * @date: 2024/2/20
 **/
@Getter
@Setter
@RelationshipProperties
@NoArgsConstructor
public class TableRelationship {

    @RelationshipId
    protected Long id;

    /**
     * 频率
     */
    private Long frequency;

    @TargetNode
    private TableNode targetTable;

    public TableRelationship(TableNode targetTable, Long frequency) {
        this.frequency = frequency;
        this.targetTable = targetTable;
    }

}
