package com.yuge.ing.shardingsphere.business;

import com.yuge.ing.shardingsphere.dto.UserDTO;
import com.yuge.ing.shardingsphere.dto.UserPageQuery;
import com.yuge.ing.shardingsphere.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  业务
 *
 * @author yuge
 * @since 2024-03-26
 */
public interface UserBusinessService {

    /**
     * 新增
     *
     * @param userDTO
     * @return 主键
     */
    void add(UserDTO userDTO);

    /**
     * 编辑
     *
     * @param userDTO
     * @return
     */
    void edit(UserDTO userDTO);

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
    IPage<UserVO> page(UserPageQuery pageQuery);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    UserVO queryDetailById(Long id);

}
