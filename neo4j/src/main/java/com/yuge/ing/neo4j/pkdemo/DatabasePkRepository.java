package com.yuge.ing.neo4j.pkdemo;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yuge
 * @date: 2024/1/18
 **/
@Repository
public interface DatabasePkRepository extends Neo4jRepository<DatabaseNode, String> {

    DatabaseNode findByDataSourceNameAndName(String dataSourceName, String name);

    List<DatabaseNode> findByName(String name);

}
