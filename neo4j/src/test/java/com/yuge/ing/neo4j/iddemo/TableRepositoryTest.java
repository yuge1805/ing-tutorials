package com.yuge.ing.neo4j.iddemo;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TableRepositoryTest {

    @Resource
    private TableRepository tableRepository;

    @Test
    public void saveTable() {
        DatabaseNode testDb = new DatabaseNode();
        testDb.setName("testDb");
        testDb.setPk("d65e4e67-5ea5-482c-9be7-2d0b68ed4c44");

        TableNode tableNode = new TableNode();
        tableNode.setName("table");

        TableNode tableNode2 = new TableNode();
        tableNode2.setName("table2");

        TableNode tableNode3 = new TableNode();
        tableNode3.setName("table3");

        tableNode.setDatabase(testDb);
        tableNode2.setDatabase(testDb);
        tableNode3.setDatabase(testDb);
        tableRepository.save(tableNode);
        tableRepository.save(tableNode2);
        tableRepository.save(tableNode3);
    }

    @Test
    public void updateTable() {
        TableNode testTable3 = tableRepository.findByName("table3");
        TableNode testTable2 = tableRepository.findByName("table2");
        TableNode testTable = tableRepository.findByName("table");

        testTable2.setTargetTables(Lists.newArrayList(testTable3));
        testTable2.setSourceTables(Lists.newArrayList(testTable));
        tableRepository.save(testTable2);

    }

    @Test
    public void updateTable2() {
        DatabaseNode testDb = new DatabaseNode();
        testDb.setName("testDb");
        testDb.setPk("d65e4e67-5ea5-482c-9be7-2d0b68ed4c44");

        TableNode tableNode = new TableNode();
        tableNode.setName("table4");

        tableNode.setDatabase(testDb);
        tableRepository.save(tableNode);


        TableNode testTable2 = tableRepository.findByName("table2");
        testTable2.getTargetTables().add(tableNode);
        tableRepository.save(testTable2);

    }

    @Test
    public void test3() {
        DatabaseNode testDb = new DatabaseNode();
        testDb.setName("testDb");
        testDb.setPk("d65e4e67-5ea5-482c-9be7-2d0b68ed4c44");

        TableNode tableNode2 = new TableNode();
        tableNode2.setName("table222");
        tableNode2.setDatabase(testDb);

        TableNode tableNode3 = new TableNode();
        tableNode3.setName("table333");
        tableNode3.setDatabase(testDb);

        TableNode tableNode = new TableNode();
        tableNode.setName("table111");
        tableNode.setDatabase(testDb);
        tableNode.setTargetRelationships(Lists.newArrayList(
                new TableRelationship(tableNode2, 222L),
                new TableRelationship(tableNode3, 111L)
        ));
        tableRepository.save(tableNode);


        TableNode testTable2 = tableRepository.findByName("table111");
        System.out.println(testTable2);

    }

    @Test
    public void test4() {
        TableNode testTable2 = tableRepository.findByName("table333");
        System.out.println(testTable2);
    }


}