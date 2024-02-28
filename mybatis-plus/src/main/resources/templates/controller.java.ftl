package ${package.Controller};

<#list controllerImportPackages as pkg>
import ${pkg};
</#list>
import ${package.BusinessService}.${businessService};
import ${package.DTO}.${dto};
import ${package.DTO}.${pageQuery};
import ${package.VO}.${vo};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

import java.util.List;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    <#assign tmpServiceName = "${businessService?uncap_first}">
    private final ${businessService} ${tmpServiceName};

    /**
     * 新增
     *
     * @param ${dto?uncap_first}
     * @return
     */
    @PostMapping
    public CommonResult<Long> add(@RequestBody @Validated ${dto} ${dto?uncap_first}) {
        return CommonResult.success(${tmpServiceName}.add(${dto?uncap_first}));
    }

    /**
     * 编辑
     *
     * @param ${dto?uncap_first}
     * @return
     */
    @PutMapping("/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id,
                                      @RequestBody @Validated ${dto} ${dto?uncap_first}) {
        ${dto?uncap_first}.setId(id);
        ${tmpServiceName}.edit(${dto?uncap_first});
        return CommonResult.success(true);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/page")
    public CommonResult<CommonPage<${vo}>> page(@RequestBody @Validated ${pageQuery} pageQuery) {
        return CommonResult.successPage(${tmpServiceName}.page(pageQuery));
    }

    /**
     * 列表
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/list")
    public CommonResult<List<${vo}>> list(@RequestBody @Validated ${pageQuery} pageQuery) {
        pageQuery.setPageNum(1L);
        pageQuery.setPageSize(200L);
        IPage<${vo}> page = ${tmpServiceName}.page(pageQuery);
        return CommonResult.success(page.getRecords());
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public CommonResult<Boolean> delete(@PathVariable("id") Long id) {
        ${tmpServiceName}.delete(Lists.newArrayList(id));
        return CommonResult.success(true);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<${vo}> detail(@PathVariable("id") Long id) {
        return CommonResult.success(${tmpServiceName}.queryDetailById(id));
    } 
 
}
</#if>
