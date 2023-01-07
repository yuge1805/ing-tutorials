package com.yuge.ing.mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuge
 * @date: 2022/12/30
 **/
public class Record {

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        List<Point> points = new ArrayList<>();

        int numberOfButtons = MouseInfo.getNumberOfButtons();
        System.out.println(numberOfButtons);

//        for (int i = 0; i < 100; i++) {
//            Point p = MouseInfo.getPointerInfo().getLocation();
//            points.add(p);
//            Thread.sleep(100);
//        }
//
//        System.out.println("===========");
//        for (Point p : points) {
//            System.out.println(p);
//            robot.mouseMove(p.x, p.y);
//            Thread.sleep(100);
//        }
    }

}
