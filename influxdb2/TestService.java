package com.central.agg.service;

import com.central.agg.entity.MpcvEntity;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.InfluxDBClientOptions;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;

public class TestService {
    public static void main(String[] args) {
        // 替换
        String url = "http://ip:port";
        String username = "admin";
        String password = "";
        String database = "energy_raw";
        String org = "nk";
        String token = "1j4wYq90ND2Q3Ow0EuH4EX7Wq-XXUAQVfdlr7sYkM4kuK3HY7BY2Ur1mGKKopFgotLc_p0NQJSqTg2qPo4sSNA==";
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, database);
        WriteApiBlocking writeApi = client.getWriteApiBlocking();

        MpcvEntity mpcvEntity = new MpcvEntity();
        mpcvEntity.setSignalId("xxx");
//        mpcvEntity.setDataTime(Instant.parse("2025-07-24T08:35:15Z"));
        mpcvEntity.setTime(Instant.now());
//        mpcvEntity.setDataTime(Instant.now());
        mpcvEntity.setValue(0.4);

        writeApi.writeMeasurement(WritePrecision.NS, mpcvEntity);

        writeApi.writeRecord(WritePrecision.NS, "mpcv,sig_id=signal001 value=15.7,original_value=15.5");

        client.close();
    }
}

//        InfluxDBClientOptions options = InfluxDBClientOptions.builder()
//                .url(url)
////                .authenticate(username, password.toCharArray())
//                .authenticateToken(token.toCharArray())
//                .org(org)
//                .build();


//            QueryApi queryApi = client.getQueryApi();
//
//            // 查询数据（使用 InfluxQL 语法）
//            String query = String.format("SELECT * FROM \"%s\".\"autogen\".\"measurement\"", database);
//            List<FluxTable> tables = queryApi.query(query);
//
//            // 处理查询结果
//            for (FluxTable table : tables) {
//                for (FluxRecord record : table.getRecords()) {
//                    System.out.println(record.getTime() + ": " + record.getValueByKey("_value"));
//                }
//            }