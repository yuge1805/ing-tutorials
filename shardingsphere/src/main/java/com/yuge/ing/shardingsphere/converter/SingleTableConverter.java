package com.yuge.ing.shardingsphere.converter;

import com.yuge.ing.shardingsphere.condition.SingleTableCondition;
import com.yuge.ing.shardingsphere.dto.SingleTableDTO;
import com.yuge.ing.shardingsphere.dto.SingleTablePageQuery;
import com.yuge.ing.shardingsphere.entity.SingleTable;
import com.yuge.ing.shardingsphere.vo.SingleTableVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 转换类
 *
 * @author yuge
 * @since 2024-04-02
 */
@Mapper
public interface SingleTableConverter {

    SingleTableConverter INSTANCE = Mappers.getMapper(SingleTableConverter.class);

    /**
     * convert dto to po
     *
     * @param singleTableDTO
     * @return
     */
    SingleTable dtoToPo(SingleTableDTO singleTableDTO);

    /**
     * convert po to vo
     *
     * @param singleTable
     * @return
     */
    SingleTableVO poToVo(SingleTable singleTable);

    /**
     * convert po to vo
     *
     * @param singleTableList
     * @return
     */
    List<SingleTableVO> poToVo(List<SingleTable> singleTableList);

    /**
     * convert po to vo
     *
     * @param page
     * @return
     */
    default IPage<SingleTableVO> poToVo(IPage<SingleTable> page) {
        return page.convert(SingleTableConverter.INSTANCE::poToVo);
    }

    /**
     * convert query to condition
     *
     * @param pageQuery
     * @return
     */
    SingleTableCondition queryToCondition(SingleTablePageQuery pageQuery);

}
