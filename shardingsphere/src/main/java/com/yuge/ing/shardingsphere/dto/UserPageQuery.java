package com.yuge.ing.shardingsphere.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 *
 * @author yuge
 * @since 2024-03-26
 */
@Getter
@Setter
public class UserPageQuery extends PageQuery {

    private Long id;

    private String name;

}
