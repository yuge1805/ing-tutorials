package com.yuge.ing.jackson.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yuge
 * @date: 2022/12/14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {

    private T data;

    public static <T> R success(T data) {
        return new R(data);
    }

}
