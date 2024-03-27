package com.yuge.ing.shardingsphere.business.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuge.ing.shardingsphere.business.UserBusinessService;
import com.yuge.ing.shardingsphere.condition.UserCondition;
import com.yuge.ing.shardingsphere.converter.UserConverter;
import com.yuge.ing.shardingsphere.dto.UserDTO;
import com.yuge.ing.shardingsphere.dto.UserPageQuery;
import com.yuge.ing.shardingsphere.entity.User;
import com.yuge.ing.shardingsphere.service.IUserService;
import com.yuge.ing.shardingsphere.vo.UserVO;
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
public class UserBusinessServiceImpl implements UserBusinessService {

    private final IUserService userService;

    /**
     * 新增
     *
     * @param userDTO
     * @return 主键
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserDTO userDTO) {
        this.checkAdd(userDTO);
        User addEntity = UserConverter.INSTANCE.dtoToPo(userDTO);
        userService.save(addEntity);
    }

    /**
     * check add
     *
     * @param addDTO
     */
    protected void checkAdd(UserDTO addDTO) {

    }

    /**
     * 编辑
     *
     * @param userDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserDTO userDTO) {
        // check
        this.checkEdit(userDTO);
        User o = userService.getById(userDTO.getId());
        if (Objects.isNull(o)) {
            throw new RuntimeException("数据不存在！");
        }

        // update
        User updateEntity = UserConverter.INSTANCE.dtoToPo(userDTO);
        userService.updateById(updateEntity);

    }

    /**
     * check edit
     *
     * @param editDTO
     */
    protected void checkEdit(UserDTO editDTO) {

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
        userService.removeByIds(ids);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @Override
    public IPage<UserVO> page(UserPageQuery pageQuery) {
        UserCondition userCondition = UserConverter.INSTANCE.queryToCondition(pageQuery);
        IPage<User> page = userService.queryByPage(userCondition);
        IPage<UserVO> resultPage = UserConverter.INSTANCE.poToVo(page);
        return resultPage;
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @Override
    public UserVO queryDetailById(Long id) {
        User o = userService.getById(id);
        if (Objects.isNull(o)) {
            throw new RuntimeException("数据不存在！");
        }
        UserVO entityVO = UserConverter.INSTANCE.poToVo(o);
        return entityVO;
    }

}
