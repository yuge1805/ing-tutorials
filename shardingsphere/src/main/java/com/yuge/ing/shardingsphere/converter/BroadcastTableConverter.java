package com.yuge.ing.shardingsphere.converter;

import com.yuge.ing.shardingsphere.condition.BroadcastTableCondition;
import com.yuge.ing.shardingsphere.dto.BroadcastTableDTO;
import com.yuge.ing.shardingsphere.dto.BroadcastTablePageQuery;
import com.yuge.ing.shardingsphere.entity.BroadcastTable;
import com.yuge.ing.shardingsphere.vo.BroadcastTableVO;
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
public interface BroadcastTableConverter {

    BroadcastTableConverter INSTANCE = Mappers.getMapper(BroadcastTableConverter.class);

    /**
     * convert dto to po
     *
     * @param broadcastTableDTO
     * @return
     */
    BroadcastTable dtoToPo(BroadcastTableDTO broadcastTableDTO);

    /**
     * convert po to vo
     *
     * @param broadcastTable
     * @return
     */
    BroadcastTableVO poToVo(BroadcastTable broadcastTable);

    /**
     * convert po to vo
     *
     * @param broadcastTableList
     * @return
     */
    List<BroadcastTableVO> poToVo(List<BroadcastTable> broadcastTableList);

    /**
     * convert po to vo
     *
     * @param page
     * @return
     */
    default IPage<BroadcastTableVO> poToVo(IPage<BroadcastTable> page) {
        return page.convert(BroadcastTableConverter.INSTANCE::poToVo);
    }

    /**
     * convert query to condition
     *
     * @param pageQuery
     * @return
     */
    BroadcastTableCondition queryToCondition(BroadcastTablePageQuery pageQuery);

}
