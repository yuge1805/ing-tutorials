package com.yuge.ing.neo4j.iddemo;

import com.yuge.ing.neo4j.generate.UUIDGenerator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Data
public class BaseNode {

    @Id
    @GeneratedValue(value = UUIDGenerator.class)
    protected String pk;

}
