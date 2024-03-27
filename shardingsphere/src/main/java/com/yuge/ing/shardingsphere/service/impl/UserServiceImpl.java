package com.yuge.ing.shardingsphere.service.impl;

import com.yuge.ing.shardingsphere.condition.UserCondition;
import com.yuge.ing.shardingsphere.entity.User;
import com.yuge.ing.shardingsphere.mapper.UserMapper;
import com.yuge.ing.shardingsphere.service.IUserService;
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
 * @since 2024-03-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * query by page
     *
     * @param userCondition
     * @return
     */
    @Override
    public IPage<User> queryByPage(UserCondition userCondition) {
        Page page = new Page<>(userCondition.getPageNum(), userCondition.getPageSize());
        page.addOrder(OrderItem.desc("id"));
        LambdaQueryWrapper<User> queryWrapper = getQueryWrapper(userCondition);
        IPage<User> result = this.page(page, queryWrapper);
        return result;
    }

    private static LambdaQueryWrapper<User> getQueryWrapper(UserCondition condition) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(condition.getId())) {
            queryWrapper.eq(User::getId, condition.getId());
        }
        if (StringUtils.isNotBlank(condition.getName())) {
            queryWrapper.like(User::getName, condition.getName());
        }
        return queryWrapper;
    }

}
