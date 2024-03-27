package com.yuge.ing.shardingsphere.converter;

import com.yuge.ing.shardingsphere.condition.OrderItemCondition;
import com.yuge.ing.shardingsphere.dto.OrderItemDTO;
import com.yuge.ing.shardingsphere.dto.OrderItemPageQuery;
import com.yuge.ing.shardingsphere.entity.OrderItem;
import com.yuge.ing.shardingsphere.vo.OrderItemVO;
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
public interface OrderItemConverter {

    OrderItemConverter INSTANCE = Mappers.getMapper(OrderItemConverter.class);

    /**
     * convert dto to po
     *
     * @param orderItemDTO
     * @return
     */
    OrderItem dtoToPo(OrderItemDTO orderItemDTO);

    /**
     * convert po to vo
     *
     * @param orderItem
     * @return
     */
    OrderItemVO poToVo(OrderItem orderItem);

    /**
     * convert po to vo
     *
     * @param orderItemList
     * @return
     */
    List<OrderItemVO> poToVo(List<OrderItem> orderItemList);

    /**
     * convert po to vo
     *
     * @param page
     * @return
     */
    default IPage<OrderItemVO> poToVo(IPage<OrderItem> page) {
        return page.convert(OrderItemConverter.INSTANCE::poToVo);
    }

    /**
     * convert query to condition
     *
     * @param pageQuery
     * @return
     */
    OrderItemCondition queryToCondition(OrderItemPageQuery pageQuery);

}
