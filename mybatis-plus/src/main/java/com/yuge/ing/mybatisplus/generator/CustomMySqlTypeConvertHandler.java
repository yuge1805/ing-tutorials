package com.yuge.ing.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.type.ITypeConvertHandler;
import com.baomidou.mybatisplus.generator.type.TypeRegistry;
import org.jetbrains.annotations.NotNull;

import static org.apache.ibatis.type.JdbcType.SMALLINT;

/**
 *
 * @author yuge
 * @since 2023-05-12
 */
public class CustomMySqlTypeConvertHandler implements ITypeConvertHandler {

    @Override
    public @NotNull IColumnType convert(GlobalConfig globalConfig, TypeRegistry typeRegistry, TableField.MetaInfo metaInfo) {
        if (metaInfo.getJdbcType() == SMALLINT) {
            return DbColumnType.INTEGER;
        }
        return typeRegistry.getColumnType(metaInfo);
    }
}
