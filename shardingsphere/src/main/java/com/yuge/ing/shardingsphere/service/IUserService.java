package com.yuge.ing.shardingsphere.service;

import com.yuge.ing.shardingsphere.condition.UserCondition;
import com.yuge.ing.shardingsphere.entity.User;
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
public interface IUserService extends IService<User> {

    /**
    * query by page
    *
    * @param userCondition
    * @return
    */
    IPage<User> queryByPage(UserCondition userCondition);

}
