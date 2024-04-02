package com.yuge.ing.shardingsphere.service;

import com.yuge.ing.shardingsphere.condition.SingleTableCondition;
import com.yuge.ing.shardingsphere.entity.SingleTable;
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
public interface ISingleTableService extends IService<SingleTable> {

    /**
    * query by page
    *
    * @param singleTableCondition
    * @return
    */
    IPage<SingleTable> queryByPage(SingleTableCondition singleTableCondition);

}
