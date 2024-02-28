package ${package.Service};

import ${package.Condition}.${condition};
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * query by page
    *
    * @param ${condition?uncap_first}
    * @return
    */
    IPage<${entity}> queryByPage(${condition} ${condition?uncap_first});

}
</#if>
