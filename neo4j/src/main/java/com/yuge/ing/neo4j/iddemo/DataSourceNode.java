package com.yuge.ing.neo4j.iddemo;

import com.yuge.ing.neo4j.constants.LineageConstants.NodeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Node(NodeType.DATASOURCE)
@Data
@NoArgsConstructor
public class DataSourceNode extends BaseNode {

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 源数据源id
     */
    private Long originDataSourceId;

    public DataSourceNode(String name) {
        this.name = name;
    }

}
