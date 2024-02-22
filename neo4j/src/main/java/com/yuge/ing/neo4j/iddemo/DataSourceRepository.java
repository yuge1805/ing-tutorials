package com.yuge.ing.neo4j.iddemo;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Repository
public interface DataSourceRepository extends Neo4jRepository<DataSourceNode, String> {


}
