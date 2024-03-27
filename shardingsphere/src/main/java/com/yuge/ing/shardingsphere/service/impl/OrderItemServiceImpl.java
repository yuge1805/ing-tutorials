package com.yuge.ing.shardingsphere.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuge.ing.shardingsphere.condition.OrderItemCondition;
import com.yuge.ing.shardingsphere.entity.OrderItem;
import com.yuge.ing.shardingsphere.mapper.OrderItemMapper;
import com.yuge.ing.shardingsphere.service.IOrderItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

    /**
     * query by page
     *
     * @param orderItemCondition
     * @return
     */
    @Override
    public IPage<OrderItem> queryByPage(OrderItemCondition orderItemCondition) {
        Page page = new Page<>(orderItemCondition.getPageNum(), orderItemCondition.getPageSize());
        LambdaQueryWrapper<OrderItem> queryWrapper = getQueryWrapper(orderItemCondition);
        IPage<OrderItem> result = this.page(page, queryWrapper);
        return result;
    }

    @Override
    public List<OrderItem> queryByOrderId(Long orderId) {
        return this.lambdaQuery().eq(OrderItem::getOrderId, orderId).list();
    }

    @Override
    public List<OrderItem> queryByOrderId(Long orderId, Long userId) {
        return this.lambdaQuery().eq(OrderItem::getOrderId, orderId)
                .eq(OrderItem::getUserId, userId)
                .list();
    }

    private static LambdaQueryWrapper<OrderItem> getQueryWrapper(OrderItemCondition condition) {
        LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(condition.getItemId())) {
            queryWrapper.eq(OrderItem::getItemId, condition.getItemId());
        }
        if (Objects.nonNull(condition.getOrderId())) {
            queryWrapper.eq(OrderItem::getOrderId, condition.getOrderId());
        }
        if (Objects.nonNull(condition.getUserId())) {
            queryWrapper.eq(OrderItem::getUserId, condition.getUserId());
        }
        if (StringUtils.isNotBlank(condition.getStatus())) {
            queryWrapper.like(OrderItem::getStatus, condition.getStatus());
        }
        return queryWrapper;
    }

}
