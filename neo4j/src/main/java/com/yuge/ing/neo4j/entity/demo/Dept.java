package com.yuge.ing.neo4j.entity.demo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author: yuge
 * @date: 2024/1/17
 **/
@Node
@Data
public class Dept {

    @Id
    @GeneratedValue
    private Long id;

    private String deptName;

}
