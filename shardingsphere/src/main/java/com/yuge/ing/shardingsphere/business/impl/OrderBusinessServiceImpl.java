package com.yuge.ing.shardingsphere.business.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuge.ing.shardingsphere.business.OrderBusinessService;
import com.yuge.ing.shardingsphere.condition.OrderCondition;
import com.yuge.ing.shardingsphere.converter.OrderConverter;
import com.yuge.ing.shardingsphere.dto.OrderDTO;
import com.yuge.ing.shardingsphere.dto.OrderPageQuery;
import com.yuge.ing.shardingsphere.entity.Order;
import com.yuge.ing.shardingsphere.service.IOrderService;
import com.yuge.ing.shardingsphere.vo.OrderVO;
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
 * @since 2024-03-26
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private final IOrderService orderService;

    /**
     * 新增
     *
     * @param orderDTO
     * @return 主键
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(OrderDTO orderDTO) {
        this.checkAdd(orderDTO);
        Order addEntity = OrderConverter.INSTANCE.dtoToPo(orderDTO);
        orderService.save(addEntity);
    }

    /**
     * check add
     *
     * @param addDTO
     */
    protected void checkAdd(OrderDTO addDTO) {

    }

    /**
     * 编辑
     *
     * @param orderDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(OrderDTO orderDTO) {
        // check
        this.checkEdit(orderDTO);
        Order o = orderService.getById(orderDTO.getOrderId());
        if (Objects.isNull(o)) {
            throw new RuntimeException("数据不存在！");
        }

        // update
        Order updateEntity = OrderConverter.INSTANCE.dtoToPo(orderDTO);
        orderService.updateById(updateEntity);

    }

    /**
     * check edit
     *
     * @param editDTO
     */
    protected void checkEdit(OrderDTO editDTO) {

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
        orderService.removeByIds(ids);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @Override
    public IPage<OrderVO> page(OrderPageQuery pageQuery) {
        OrderCondition orderCondition = OrderConverter.INSTANCE.queryToCondition(pageQuery);
        IPage<Order> page = orderService.queryByPage(orderCondition);
        IPage<OrderVO> resultPage = OrderConverter.INSTANCE.poToVo(page);
        return resultPage;
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @Override
    public OrderVO queryDetailById(Long id) {
        Order o = orderService.getById(id);
        if (Objects.isNull(o)) {
            throw new RuntimeException("数据不存在！");
        }
        OrderVO entityVO = OrderConverter.INSTANCE.poToVo(o);
        return entityVO;
    }

}
