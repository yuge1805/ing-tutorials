package com.yuge.ing.shardingsphere.service;

import com.yuge.ing.shardingsphere.condition.OrderCondition;
import com.yuge.ing.shardingsphere.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuge
 * @since 2024-03-26
 */
public interface IOrderService extends IService<Order> {

    /**
    * query by page
    *
    * @param orderCondition
    * @return
    */
    IPage<Order> queryByPage(OrderCondition orderCondition);

}
