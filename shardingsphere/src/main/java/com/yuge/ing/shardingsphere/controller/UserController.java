package com.yuge.ing.shardingsphere.controller;

import com.yuge.ing.shardingsphere.business.UserBusinessService;
import com.yuge.ing.shardingsphere.core.CommonPage;
import com.yuge.ing.shardingsphere.core.CommonResult;
import com.yuge.ing.shardingsphere.dto.UserDTO;
import com.yuge.ing.shardingsphere.dto.UserPageQuery;
import com.yuge.ing.shardingsphere.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * user
 *
 * @author yuge
 * @since 2024-03-26
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserBusinessService userBusinessService;

    /**
     * 新增
     *
     * @param userDTO
     * @return
     */
    @PostMapping
    public CommonResult<Boolean> add(@RequestBody @Validated UserDTO userDTO) {
        userBusinessService.add(userDTO);
        return CommonResult.success(true);
    }

    /**
     * 编辑
     *
     * @param userDTO
     * @return
     */
    @PutMapping("/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id,
                                      @RequestBody @Validated UserDTO userDTO) {
        userDTO.setId(id);
        userBusinessService.edit(userDTO);
        return CommonResult.success(true);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/page")
    public CommonResult<CommonPage<UserVO>> page(@RequestBody @Validated UserPageQuery pageQuery) {
        return CommonResult.successPage(userBusinessService.page(pageQuery));
    }

    /**
     * 列表
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/list")
    public CommonResult<List<UserVO>> list(@RequestBody @Validated UserPageQuery pageQuery) {
        pageQuery.setPageNum(1L);
        pageQuery.setPageSize(200L);
        IPage<UserVO> page = userBusinessService.page(pageQuery);
        return CommonResult.success(page.getRecords());
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public CommonResult<Boolean> delete(@PathVariable("id") Long id) {
        userBusinessService.delete(Lists.newArrayList(id));
        return CommonResult.success(true);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<UserVO> detail(@PathVariable("id") Long id) {
        return CommonResult.success(userBusinessService.queryDetailById(id));
    } 
 
}
