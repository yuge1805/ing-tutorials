package com.yuge.ing.shardingsphere.business;

import com.yuge.ing.shardingsphere.dto.OrderDTO;
import com.yuge.ing.shardingsphere.dto.OrderPageQuery;
import com.yuge.ing.shardingsphere.vo.OrderVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  业务
 *
 * @author yuge
 * @since 2024-03-26
 */
public interface OrderBusinessService {

    /**
     * 新增
     *
     * @param orderDTO
     * @return 主键
     */
    void add(OrderDTO orderDTO);

    /**
     * 编辑
     *
     * @param orderDTO
     * @return
     */
    void edit(OrderDTO orderDTO);

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
    IPage<OrderVO> page(OrderPageQuery pageQuery);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    OrderVO queryDetailById(Long id);

}
