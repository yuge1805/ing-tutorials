package com.yuge.ing.shardingsphere.business;

import com.yuge.ing.shardingsphere.dto.SingleTableDTO;
import com.yuge.ing.shardingsphere.dto.SingleTablePageQuery;
import com.yuge.ing.shardingsphere.vo.SingleTableVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  业务
 *
 * @author yuge
 * @since 2024-04-02
 */
public interface SingleTableBusinessService {

    /**
     * 新增
     *
     * @param singleTableDTO
     * @return 主键
     */
    Long add(SingleTableDTO singleTableDTO);

    /**
     * 编辑
     *
     * @param singleTableDTO
     * @return
     */
    void edit(SingleTableDTO singleTableDTO);

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
    IPage<SingleTableVO> page(SingleTablePageQuery pageQuery);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SingleTableVO queryDetailById(Long id);

}
