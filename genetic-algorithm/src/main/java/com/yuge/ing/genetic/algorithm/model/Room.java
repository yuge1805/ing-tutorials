package com.yuge.ing.genetic.algorithm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教室
 *
 * @author: yuge
 * @date: 2022/12/29
 **/
@Data
@NoArgsConstructor
public class Room {

    /**
     * id
     */
    private Long id;

    /**
     * 教室名
     */
    private String roomName;

    /**
     * 教室默认班级id
     */
    private Long roomDefaultClassId;

    public Room(Long id, String roomName, Long roomDefaultClassId) {
        this.id = id;
        this.roomName = roomName;
        this.roomDefaultClassId = roomDefaultClassId;
    }
}
