package com.yuge.ing.neo4j.iddemo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Data
public class BaseNode {

    @Id
    @GeneratedValue
    protected Long id;

}
