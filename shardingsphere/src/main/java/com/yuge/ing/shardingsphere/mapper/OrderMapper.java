package com.yuge.ing.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuge.ing.shardingsphere.condition.OrderCondition;
import com.yuge.ing.shardingsphere.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuge.ing.shardingsphere.vo.OrderVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuge
 * @since 2024-03-26
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 分页查询
     *
     * @param page
     * @param orderCondition
     * @return
     */
    IPage<OrderVO> queryVoByPage(IPage<OrderVO> page, @Param("q") OrderCondition orderCondition);

}
