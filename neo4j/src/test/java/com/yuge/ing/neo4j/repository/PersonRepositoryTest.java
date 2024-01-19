package com.yuge.ing.neo4j.repository;

import com.yuge.ing.neo4j.entity.demo.Person;
import com.yuge.ing.neo4j.pkdemo.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PersonRepositoryTest {

    @Resource
    private PersonRepository personRepository;

    @Test
    public void testAdd() {
        Person person = new Person();
        person.setName("yuge");
        personRepository.save(person);
        System.out.println(person);
    }

}