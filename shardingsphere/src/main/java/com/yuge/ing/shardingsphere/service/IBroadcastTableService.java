package com.yuge.ing.shardingsphere.service;

import com.yuge.ing.shardingsphere.condition.BroadcastTableCondition;
import com.yuge.ing.shardingsphere.entity.BroadcastTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuge
 * @since 2024-04-02
 */
public interface IBroadcastTableService extends IService<BroadcastTable> {

    /**
    * query by page
    *
    * @param broadcastTableCondition
    * @return
    */
    IPage<BroadcastTable> queryByPage(BroadcastTableCondition broadcastTableCondition);

}
