package ${package.ServiceImpl};

import ${package.Condition}.${condition};
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    /**
     * query by page
     *
     * @param ${condition?uncap_first}
     * @return
     */
    @Override
    public IPage<${entity}> queryByPage(${condition} ${condition?uncap_first}) {
        Page page = new Page<>(${condition?uncap_first}.getPageNum(), ${condition?uncap_first}.getPageSize());
        page.addOrder(OrderItem.desc("id"));
        LambdaQueryWrapper<${entity}> queryWrapper = getQueryWrapper(${condition?uncap_first});
        IPage<${entity}> result = this.page(page, queryWrapper);
        return result;
    }

    private static LambdaQueryWrapper<${entity}> getQueryWrapper(${condition} condition) {
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
<#assign eqTypeList = ["Byte", "Short", "Character", "Integer", "Long", "Boolean"]>
<#assign likeTypeList = ["String"]>
<#list table.fields as field>
    <#assign capPropertyName="${field.propertyName?cap_first}" >
    <#if eqTypeList?seq_contains(field.columnType.type)>
        if (Objects.nonNull(condition.get${capPropertyName}())) {
            queryWrapper.eq(${entity}::get${capPropertyName}, condition.get${capPropertyName}());
        }
    <#elseif likeTypeList?seq_contains(field.columnType.type)>
        if (StringUtils.isNotBlank(condition.get${capPropertyName}())) {
            queryWrapper.like(${entity}::get${capPropertyName}, condition.get${capPropertyName}());
        }
    </#if>
</#list>
        return queryWrapper;
    }

}
</#if>
