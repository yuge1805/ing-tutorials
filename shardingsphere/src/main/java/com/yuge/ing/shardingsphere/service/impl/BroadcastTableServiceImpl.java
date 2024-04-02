package com.yuge.ing.shardingsphere.service.impl;

import com.yuge.ing.shardingsphere.condition.BroadcastTableCondition;
import com.yuge.ing.shardingsphere.entity.BroadcastTable;
import com.yuge.ing.shardingsphere.mapper.BroadcastTableMapper;
import com.yuge.ing.shardingsphere.service.IBroadcastTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuge
 * @since 2024-04-02
 */
@Service
public class BroadcastTableServiceImpl extends ServiceImpl<BroadcastTableMapper, BroadcastTable> implements IBroadcastTableService {

    /**
     * query by page
     *
     * @param broadcastTableCondition
     * @return
     */
    @Override
    public IPage<BroadcastTable> queryByPage(BroadcastTableCondition broadcastTableCondition) {
        Page page = new Page<>(broadcastTableCondition.getPageNum(), broadcastTableCondition.getPageSize());
        page.addOrder(OrderItem.desc("id"));
        LambdaQueryWrapper<BroadcastTable> queryWrapper = getQueryWrapper(broadcastTableCondition);
        IPage<BroadcastTable> result = this.page(page, queryWrapper);
        return result;
    }

    private static LambdaQueryWrapper<BroadcastTable> getQueryWrapper(BroadcastTableCondition condition) {
        LambdaQueryWrapper<BroadcastTable> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(condition.getId())) {
            queryWrapper.eq(BroadcastTable::getId, condition.getId());
        }
        if (StringUtils.isNotBlank(condition.getName())) {
            queryWrapper.like(BroadcastTable::getName, condition.getName());
        }
        if (StringUtils.isNotBlank(condition.getStatus())) {
            queryWrapper.like(BroadcastTable::getStatus, condition.getStatus());
        }
        return queryWrapper;
    }

}
