package com.yuge.ing.neo4j.pkdemo;

import com.yuge.ing.neo4j.constants.LineageConstants.NodeType;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Node(NodeType.DATASOURCE + "pk")
@Data
public class DataSourceNode extends BaseNode {

    private Long id;

    /**
     * 数据源名称
     */
    private String name;

    public DataSourceNode(String name) {
        this.name = name;
        this.pk = name;
    }

}
