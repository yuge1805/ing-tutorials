package com.yuge.ing.shardingsphere.service.impl;

import com.yuge.ing.shardingsphere.condition.SingleTableCondition;
import com.yuge.ing.shardingsphere.entity.SingleTable;
import com.yuge.ing.shardingsphere.mapper.SingleTableMapper;
import com.yuge.ing.shardingsphere.service.ISingleTableService;
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
public class SingleTableServiceImpl extends ServiceImpl<SingleTableMapper, SingleTable> implements ISingleTableService {

    /**
     * query by page
     *
     * @param singleTableCondition
     * @return
     */
    @Override
    public IPage<SingleTable> queryByPage(SingleTableCondition singleTableCondition) {
        Page page = new Page<>(singleTableCondition.getPageNum(), singleTableCondition.getPageSize());
        page.addOrder(OrderItem.desc("id"));
        LambdaQueryWrapper<SingleTable> queryWrapper = getQueryWrapper(singleTableCondition);
        IPage<SingleTable> result = this.page(page, queryWrapper);
        return result;
    }

    private static LambdaQueryWrapper<SingleTable> getQueryWrapper(SingleTableCondition condition) {
        LambdaQueryWrapper<SingleTable> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(condition.getId())) {
            queryWrapper.eq(SingleTable::getId, condition.getId());
        }
        if (StringUtils.isNotBlank(condition.getName())) {
            queryWrapper.like(SingleTable::getName, condition.getName());
        }
        if (StringUtils.isNotBlank(condition.getStatus())) {
            queryWrapper.like(SingleTable::getStatus, condition.getStatus());
        }
        return queryWrapper;
    }

}
