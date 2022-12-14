package com.yuge.ing.mybatisplus.generation;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.type.ITypeConvertHandler;
import com.baomidou.mybatisplus.generator.type.TypeRegistry;
import org.apache.ibatis.type.JdbcType;
import org.jetbrains.annotations.NotNull;

/**
 * @author: yuge
 * @date: 2022/11/10
 **/
public class CustomTypeConvertHandler implements ITypeConvertHandler {

    @NotNull
    @Override
    public IColumnType convert(GlobalConfig globalConfig, TypeRegistry typeRegistry, TableField.MetaInfo metaInfo) {
        if (metaInfo.getJdbcType() == JdbcType.SMALLINT) {
            return DbColumnType.INTEGER;
        }
        return null;
    }

}
