package com.yuge.ing.neo4j.iddemo;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Repository
public interface DatabaseRepository extends Neo4jRepository<DatabaseNode, Long> {

    DatabaseNode findByDataSourceNameAndName(String dataSourceName, String name);

    List<DatabaseNode> findByName(String name);

}
