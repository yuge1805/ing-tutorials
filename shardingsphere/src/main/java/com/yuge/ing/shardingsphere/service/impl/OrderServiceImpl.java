package com.yuge.ing.shardingsphere.service.impl;

import com.yuge.ing.shardingsphere.condition.OrderCondition;
import com.yuge.ing.shardingsphere.entity.Order;
import com.yuge.ing.shardingsphere.mapper.OrderMapper;
import com.yuge.ing.shardingsphere.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuge.ing.shardingsphere.vo.OrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuge
 * @since 2024-03-26
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    /**
     * query by page
     *
     * @param orderCondition
     * @return
     */
    @Override
    public IPage<Order> queryByPage(OrderCondition orderCondition) {
        Page page = new Page<>(orderCondition.getPageNum(), orderCondition.getPageSize());
        page.addOrder(OrderItem.desc("order_id"));
        LambdaQueryWrapper<Order> queryWrapper = getQueryWrapper(orderCondition);
        IPage<Order> result = this.page(page, queryWrapper);
        return result;
    }

    /**
     * query vo by page
     *
     * @param orderCondition
     * @return
     */
    @Override
    public IPage<OrderVO> queryVoByPage(OrderCondition orderCondition) {
        Page page = new Page<>(orderCondition.getPageNum(), orderCondition.getPageSize());
        page.addOrder(OrderItem.desc("order_id"));
        page.setOptimizeJoinOfCountSql(false);
        return getBaseMapper().queryVoByPage(page, orderCondition);
    }

    private static LambdaQueryWrapper<Order> getQueryWrapper(OrderCondition condition) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(condition.getOrderId())) {
            queryWrapper.eq(Order::getOrderId, condition.getOrderId());
        }
        if (Objects.nonNull(condition.getUserId())) {
            queryWrapper.eq(Order::getUserId, condition.getUserId());
        }
        if (StringUtils.isNotBlank(condition.getStatus())) {
            queryWrapper.like(Order::getStatus, condition.getStatus());
        }
        return queryWrapper;
    }

}
