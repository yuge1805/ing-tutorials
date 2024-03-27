package com.yuge.ing.shardingsphere.converter;

import com.yuge.ing.shardingsphere.condition.UserCondition;
import com.yuge.ing.shardingsphere.dto.UserDTO;
import com.yuge.ing.shardingsphere.dto.UserPageQuery;
import com.yuge.ing.shardingsphere.entity.User;
import com.yuge.ing.shardingsphere.vo.UserVO;
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
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * convert dto to po
     *
     * @param userDTO
     * @return
     */
    User dtoToPo(UserDTO userDTO);

    /**
     * convert po to vo
     *
     * @param user
     * @return
     */
    UserVO poToVo(User user);

    /**
     * convert po to vo
     *
     * @param userList
     * @return
     */
    List<UserVO> poToVo(List<User> userList);

    /**
     * convert po to vo
     *
     * @param page
     * @return
     */
    default IPage<UserVO> poToVo(IPage<User> page) {
        return page.convert(UserConverter.INSTANCE::poToVo);
    }

    /**
     * convert query to condition
     *
     * @param pageQuery
     * @return
     */
    UserCondition queryToCondition(UserPageQuery pageQuery);

}
