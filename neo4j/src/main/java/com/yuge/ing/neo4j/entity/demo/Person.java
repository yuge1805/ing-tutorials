package com.yuge.ing.neo4j.entity.demo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author: yuge
 * @date: 2024/1/17
 **/
@Node
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

}
