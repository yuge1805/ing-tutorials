package com.yuge.ing.mybatisplus.generation;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import org.jetbrains.annotations.NotNull;

/**
 * @author: yuge
 * @date: 2022/11/10
 **/
public class CustomMySqlTypeConvert extends MySqlTypeConvert {

    @Override
    public IColumnType processTypeConvert(@NotNull GlobalConfig globalConfig, @NotNull TableField tableField) {
        String type = tableField.getType();
        if (type.contains("smallint")) {
            return DbColumnType.INTEGER;
        }
        return super.processTypeConvert(globalConfig, tableField);
    }

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        String s = fieldType.toLowerCase();
        if (s.contains("smallint")) {
            return DbColumnType.INTEGER;
        }
        return super.processTypeConvert(config, fieldType);
    }


}
