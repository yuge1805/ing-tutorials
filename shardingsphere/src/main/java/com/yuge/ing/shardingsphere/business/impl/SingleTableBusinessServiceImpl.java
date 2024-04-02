package com.yuge.ing.shardingsphere.business.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuge.ing.shardingsphere.business.SingleTableBusinessService;
import com.yuge.ing.shardingsphere.condition.SingleTableCondition;
import com.yuge.ing.shardingsphere.converter.SingleTableConverter;
import com.yuge.ing.shardingsphere.dto.SingleTableDTO;
import com.yuge.ing.shardingsphere.dto.SingleTablePageQuery;
import com.yuge.ing.shardingsphere.entity.SingleTable;
import com.yuge.ing.shardingsphere.service.ISingleTableService;
import com.yuge.ing.shardingsphere.vo.SingleTableVO;
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
public class SingleTableBusinessServiceImpl implements SingleTableBusinessService {

    private final ISingleTableService singleTableService;

    /**
     * 新增
     *
     * @param singleTableDTO
     * @return 主键
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long add(SingleTableDTO singleTableDTO) {
        this.checkAdd(singleTableDTO);
        SingleTable addEntity = SingleTableConverter.INSTANCE.dtoToPo(singleTableDTO);
        singleTableService.save(addEntity);
        return addEntity.getId();
    }

    /**
     * check add
     *
     * @param addDTO
     */
    protected void checkAdd(SingleTableDTO addDTO) {

    }

    /**
     * 编辑
     *
     * @param singleTableDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SingleTableDTO singleTableDTO) {
        // check
        this.checkEdit(singleTableDTO);
        SingleTable o = singleTableService.getById(singleTableDTO.getId());
        if (Objects.isNull(o)) {
            throw new RuntimeException("数据不存在！");
        }

        // update
        SingleTable updateEntity = SingleTableConverter.INSTANCE.dtoToPo(singleTableDTO);
        singleTableService.updateById(updateEntity);

    }

    /**
     * check edit
     *
     * @param editDTO
     */
    protected void checkEdit(SingleTableDTO editDTO) {

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
        singleTableService.removeByIds(ids);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @Override
    public IPage<SingleTableVO> page(SingleTablePageQuery pageQuery) {
        SingleTableCondition singleTableCondition = SingleTableConverter.INSTANCE.queryToCondition(pageQuery);
        IPage<SingleTable> page = singleTableService.queryByPage(singleTableCondition);
        IPage<SingleTableVO> resultPage = SingleTableConverter.INSTANCE.poToVo(page);
        return resultPage;
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @Override
    public SingleTableVO queryDetailById(Long id) {
        SingleTable o = singleTableService.getById(id);
        if (Objects.isNull(o)) {
            throw new RuntimeException("数据不存在！");
        }
        SingleTableVO entityVO = SingleTableConverter.INSTANCE.poToVo(o);
        return entityVO;
    }

}
