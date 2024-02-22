-- 节点属性唯一性约束
-- https://neo4j.com/docs/cypher-manual/current/constraints/examples/#constraints-examples-node-uniqueness
CREATE CONSTRAINT book_isbn IF NOT EXISTS
FOR (book:Book) REQUIRE book.isbn IS UNIQUE





