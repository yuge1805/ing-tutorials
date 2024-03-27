package com.yuge.ing.shardingsphere.converter;

import com.yuge.ing.shardingsphere.condition.OrderCondition;
import com.yuge.ing.shardingsphere.dto.OrderDTO;
import com.yuge.ing.shardingsphere.dto.OrderPageQuery;
import com.yuge.ing.shardingsphere.entity.Order;
import com.yuge.ing.shardingsphere.vo.OrderVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 转换类
 *
 * @author yuge
 * @since 2024-03-26
 */
@Mapper
public interface OrderConverter {

    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    /**
     * convert dto to po
     *
     * @param orderDTO
     * @return
     */
    Order dtoToPo(OrderDTO orderDTO);

    /**
     * convert po to vo
     *
     * @param order
     * @return
     */
    OrderVO poToVo(Order order);

    /**
     * convert po to vo
     *
     * @param orderList
     * @return
     */
    List<OrderVO> poToVo(List<Order> orderList);

    /**
     * convert po to vo
     *
     * @param page
     * @return
     */
    default IPage<OrderVO> poToVo(IPage<Order> page) {
        return page.convert(OrderConverter.INSTANCE::poToVo);
    }

    /**
     * convert query to condition
     *
     * @param pageQuery
     * @return
     */
    OrderCondition queryToCondition(OrderPageQuery pageQuery);

}
