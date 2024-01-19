package com.yuge.ing.neo4j.pkdemo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Data
public class BaseNode {

    @Id
    protected String pk;

}
