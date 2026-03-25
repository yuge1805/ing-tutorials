//package com.central.agg.service;
//
//import com.central.agg.entity.MpcvEntity;
//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.dto.Point;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author zhangbw
// * @since 2025/7/23
// */
//public class TestService1x {
//
//    public static void main(String[] args) {
//        String url = "http://ip:port";
//        String username = "admin";
//        String password = "";
////        String database = "data_collect";  // 1.x 的数据库名
//        String database = "test";  // 1.x 的数据库名
//
////        String url = "http://ip:port";
////        String username = "admin";
////        String password = "admin";
////        String database = "test";  // 1.x 的数据库名
//
//        InfluxDB influxDB = InfluxDBFactory.connect(url, username, password);
//        influxDB.setDatabase(database);
//        influxDB.setRetentionPolicy("autogen");
//        influxDB.disableBatch();
//        influxDB.setLogLevel(InfluxDB.LogLevel.FULL); // 日志级别提高看看写入日志
//
//        MpcvEntity mpcvEntity = new MpcvEntity();
//        mpcvEntity.setSignalId("xxx");
//        mpcvEntity.setDataTime(Instant.parse("2025-08-03T08:35:15Z"));
//        mpcvEntity.setValue(0.1);
//
//        Point point = Point.measurementByPOJO(MpcvEntity.class)
//                .time(mpcvEntity.getDataTime().toEpochMilli(), TimeUnit.MILLISECONDS)
//                .addFieldsFromPOJO(mpcvEntity)
//                .build();
//        influxDB.write(point);
//
//
//    }
//
//
//}
