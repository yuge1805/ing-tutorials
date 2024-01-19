package com.yuge.ing.neo4j.pkdemo;

import com.yuge.ing.neo4j.entity.demo.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: yuge
 * @date: 2024/1/17
 **/
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {



}
