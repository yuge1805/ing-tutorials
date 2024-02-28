package ${package.BusinessServiceImpl};

<#list businessServiceImplImportPackages as pkg>
import ${pkg};
</#list>
import ${package.BusinessService}.${businessService};
import ${package.Condition}.${condition};
import ${package.Converter}.${converter};
import ${package.DTO}.${dto};
import ${package.DTO}.${pageQuery};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${package.VO}.${vo};
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * ${table.comment!} 业务
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ${businessServiceImpl} implements ${businessService} {

    <#assign tmpServiceName = "${table.serviceName?substring(1)?uncap_first}">
    private final ${table.serviceName} ${tmpServiceName};

    /**
     * 新增
     *
     * @param ${dto?uncap_first}
     * @return 主键
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long add(${dto} ${dto?uncap_first}) {
        this.checkAdd(${dto?uncap_first});
        ${entity} addEntity = ${converter}.INSTANCE.dtoToPo(${dto?uncap_first});
        ${tmpServiceName}.save(addEntity);
        return addEntity.getId();
    }

    /**
     * check add
     *
     * @param addDTO
     */
    protected void checkAdd(${dto} addDTO) {

    }

    /**
     * 编辑
     *
     * @param ${dto?uncap_first}
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(${dto} ${dto?uncap_first}) {
        // check
        this.checkEdit(${dto?uncap_first});
        Optional<${entity}> optional = ${tmpServiceName}.queryById(${dto?uncap_first}.getId());
        if (!optional.isPresent()) {
            throw new ApiException(ResultCode.VALIDATE_FAILED, "数据不存在！");
        }

        // update
        ${entity} updateEntity = ${converter}.INSTANCE.dtoToPo(${dto?uncap_first});
        ${tmpServiceName}.updateById(updateEntity);

    }

    /**
     * check edit
     *
     * @param editDTO
     */
    protected void checkEdit(${dto} editDTO) {

    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        ${tmpServiceName}.removeByIds(ids);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @Override
    public IPage<${vo}> page(${pageQuery} pageQuery) {
        ${condition} ${condition?uncap_first} = ${converter}.INSTANCE.queryToCondition(pageQuery);
        IPage<${entity}> page = ${tmpServiceName}.queryByPage(${condition?uncap_first});
        IPage<${vo}> resultPage = ${converter}.INSTANCE.poToVo(page);
        return resultPage;
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @Override
    public ${vo} queryDetailById(Long id) {
        Optional<${entity}> optional = ${tmpServiceName}.queryById(id);
        if (!optional.isPresent()) {
            throw new ApiException(ResultCode.VALIDATE_FAILED, "数据不存在！");
        }
        ${vo} entityVO = ${converter}.INSTANCE.poToVo(optional.get());
        return entityVO;
    }

}
