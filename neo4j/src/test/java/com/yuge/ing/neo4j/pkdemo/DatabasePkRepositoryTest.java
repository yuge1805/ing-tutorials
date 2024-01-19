package com.yuge.ing.neo4j.pkdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DatabasePkRepositoryTest {

    @Resource
    private PersonRepository personRepository;

    @Resource
    private DatabasePkRepository databaseRepository;

    @Resource
    private DataSourcePkRepository dataSourceRepository;

    @Test
    public void testAddDataSource() {
        DataSourceNode dataSourceNode = new DataSourceNode("test");
        dataSourceRepository.save(dataSourceNode);
        System.out.println(dataSourceNode);

        DataSourceNode dataSourceNode2 = new DataSourceNode("test");
        dataSourceRepository.save(dataSourceNode2);
        System.out.println(dataSourceNode2);
    }

    @Test
    public void testAddDatabase() {
        DataSourceNode dataSourceNode = new DataSourceNode("test");

        DatabaseNode databaseNode = new DatabaseNode("testDb", dataSourceNode);
        databaseRepository.save(databaseNode);
        System.out.println(databaseNode);
    }

    @Test
    public void testFindByName() {
        List<DatabaseNode> list = databaseRepository.findByName("testDb");
        System.out.println(list);
    }

    @Test
    public void testFindByDataSourceNameAndName() {
        DatabaseNode ddd = databaseRepository.findByDataSourceNameAndName("test2", "testDb");
        System.out.println(ddd);
    }

}