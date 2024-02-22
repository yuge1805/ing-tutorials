package com.yuge.ing.neo4j.iddemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DatabaseRepositoryTest {

    @Resource
    private DatabaseRepository databaseRepository;

    @Resource
    private DataSourceRepository dataSourceRepository;

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
        DataSourceNode dataSourceNode = new DataSourceNode();
//        dataSourceNode.setId(3L);

        DatabaseNode databaseNode = new DatabaseNode();
        databaseNode.setName("testDb");
        databaseNode.setDataSource(dataSourceNode);
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