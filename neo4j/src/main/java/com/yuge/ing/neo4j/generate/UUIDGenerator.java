package com.yuge.ing.neo4j.generate;

import org.springframework.data.neo4j.core.schema.IdGenerator;

import java.util.UUID;

/**
 * @author: yuge
 * @date: 2024/1/22
 **/
public class UUIDGenerator implements IdGenerator<String> {

    @Override
    public String generateId(String primaryLabel, Object entity) {
        return UUID.randomUUID().toString();
    }

}
