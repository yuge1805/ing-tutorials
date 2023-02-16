package com.yuge.ing.poi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: yuge
 * @date: 2023/2/16
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String studentName;

    private String address;

    private String email;

    private Integer age;

}
