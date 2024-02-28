package ${package.BusinessService};

import ${package.DTO}.${dto};
import ${package.DTO}.${pageQuery};
import ${package.VO}.${vo};
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * ${table.comment!} 业务
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${businessService} {

    /**
     * 新增
     *
     * @param ${dto?uncap_first}
     * @return 主键
     */
    Long add(${dto} ${dto?uncap_first});

    /**
     * 编辑
     *
     * @param ${dto?uncap_first}
     * @return
     */
    void edit(${dto} ${dto?uncap_first});

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    void delete(List<Long> ids);

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    IPage<${vo}> page(${pageQuery} pageQuery);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    ${vo} queryDetailById(Long id);

}
