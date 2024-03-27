package com.yuge.ing.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuge
 * @since 2024-03-26
 */
@Getter
@Setter
@TableName("t_user")
public class User {

    @TableId("id")
    private Long id;

    @TableField("name")
    private String name;
}
