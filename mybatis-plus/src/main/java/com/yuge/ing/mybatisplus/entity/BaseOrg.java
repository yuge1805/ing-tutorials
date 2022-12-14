package com.yuge.ing.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import com.yuge.ing.mybatisplus.enums.RegionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 组织
 * </p>
 *
 * @author yuge
 * @since 2022-10-22
 */
@Getter
@Setter
@TableName("base_org")
public class BaseOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 行政区域（枚举值）
     */
    @TableField(value = "region", typeHandler = MybatisEnumTypeHandler.class)
    private RegionEnum region;
}
