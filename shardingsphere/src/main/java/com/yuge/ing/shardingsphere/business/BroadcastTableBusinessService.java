package com.yuge.ing.shardingsphere.business;

import com.yuge.ing.shardingsphere.dto.BroadcastTableDTO;
import com.yuge.ing.shardingsphere.dto.BroadcastTablePageQuery;
import com.yuge.ing.shardingsphere.vo.BroadcastTableVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  业务
 *
 * @author yuge
 * @since 2024-04-02
 */
public interface BroadcastTableBusinessService {

    /**
     * 新增
     *
     * @param broadcastTableDTO
     * @return 主键
     */
    Long add(BroadcastTableDTO broadcastTableDTO);

    /**
     * 编辑
     *
     * @param broadcastTableDTO
     * @return
     */
    void edit(BroadcastTableDTO broadcastTableDTO);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    void delete(List<Long> ids);

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    IPage<BroadcastTableVO> page(BroadcastTablePageQuery pageQuery);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    BroadcastTableVO queryDetailById(Long id);

}
