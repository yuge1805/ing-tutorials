package com.yuge.ing.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * character set demo
 * </p>
 *
 * @author yuge
 * @since 2023-08-23
 */
@Getter
@Setter
@TableName("character_set_demo")
public class CharacterSetDemo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 信息
     */
    @TableField("content")
    private String content;

}
