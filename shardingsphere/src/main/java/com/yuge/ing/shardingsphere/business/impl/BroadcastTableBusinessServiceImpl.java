package com.yuge.ing.shardingsphere.business.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuge.ing.shardingsphere.business.BroadcastTableBusinessService;
import com.yuge.ing.shardingsphere.condition.BroadcastTableCondition;
import com.yuge.ing.shardingsphere.converter.BroadcastTableConverter;
import com.yuge.ing.shardingsphere.dto.BroadcastTableDTO;
import com.yuge.ing.shardingsphere.dto.BroadcastTablePageQuery;
import com.yuge.ing.shardingsphere.entity.BroadcastTable;
import com.yuge.ing.shardingsphere.service.IBroadcastTableService;
import com.yuge.ing.shardingsphere.vo.BroadcastTableVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 *  业务
 *
 * @author yuge
 * @since 2024-04-02
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BroadcastTableBusinessServiceImpl implements BroadcastTableBusinessService {

    private final IBroadcastTableService broadcastTableService;

    /**
     * 新增
     *
     * @param broadcastTableDTO
     * @return 主键
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long add(BroadcastTableDTO broadcastTableDTO) {
        this.checkAdd(broadcastTableDTO);
        BroadcastTable addEntity = BroadcastTableConverter.INSTANCE.dtoToPo(broadcastTableDTO);
        broadcastTableService.save(addEntity);
        return addEntity.getId();
    }

    /**
     * check add
     *
     * @param addDTO
     */
    protected void checkAdd(BroadcastTableDTO addDTO) {

    }

    /**
     * 编辑
     *
     * @param broadcastTableDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BroadcastTableDTO broadcastTableDTO) {
        // check
        this.checkEdit(broadcastTableDTO);
        BroadcastTable o = broadcastTableService.getById(broadcastTableDTO.getId());
        if (Objects.isNull(o)) {
            throw new RuntimeException("数据不存在！");
        }

        // update
        BroadcastTable updateEntity = BroadcastTableConverter.INSTANCE.dtoToPo(broadcastTableDTO);
        broadcastTableService.updateById(updateEntity);

    }

    /**
     * check edit
     *
     * @param editDTO
     */
    protected void checkEdit(BroadcastTableDTO editDTO) {

    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        broadcastTableService.removeByIds(ids);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @Override
    public IPage<BroadcastTableVO> page(BroadcastTablePageQuery pageQuery) {
        BroadcastTableCondition broadcastTableCondition = BroadcastTableConverter.INSTANCE.queryToCondition(pageQuery);
        IPage<BroadcastTable> page = broadcastTableService.queryByPage(broadcastTableCondition);
        IPage<BroadcastTableVO> resultPage = BroadcastTableConverter.INSTANCE.poToVo(page);
        return resultPage;
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @Override
    public BroadcastTableVO queryDetailById(Long id) {
        BroadcastTable o = broadcastTableService.getById(id);
        if (Objects.isNull(o)) {
            throw new RuntimeException("数据不存在！");
        }
        BroadcastTableVO entityVO = BroadcastTableConverter.INSTANCE.poToVo(o);
        return entityVO;
    }

}
