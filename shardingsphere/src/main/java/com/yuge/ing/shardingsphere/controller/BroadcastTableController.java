package com.yuge.ing.shardingsphere.controller;

import com.yuge.ing.shardingsphere.business.BroadcastTableBusinessService;
import com.yuge.ing.shardingsphere.core.CommonPage;
import com.yuge.ing.shardingsphere.core.CommonResult;
import com.yuge.ing.shardingsphere.dto.BroadcastTableDTO;
import com.yuge.ing.shardingsphere.dto.BroadcastTablePageQuery;
import com.yuge.ing.shardingsphere.vo.BroadcastTableVO;
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
 * broadcast table
 *
 * @author yuge
 * @since 2024-04-02
 */
@RestController
@RequestMapping("/broadcastTable")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BroadcastTableController {

    private final BroadcastTableBusinessService broadcastTableBusinessService;

    /**
     * 新增
     *
     * @param broadcastTableDTO
     * @return
     */
    @PostMapping
    public CommonResult<Long> add(@RequestBody @Validated BroadcastTableDTO broadcastTableDTO) {
        return CommonResult.success(broadcastTableBusinessService.add(broadcastTableDTO));
    }

    /**
     * 编辑
     *
     * @param broadcastTableDTO
     * @return
     */
    @PutMapping("/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id,
                                      @RequestBody @Validated BroadcastTableDTO broadcastTableDTO) {
        broadcastTableDTO.setId(id);
        broadcastTableBusinessService.edit(broadcastTableDTO);
        return CommonResult.success(true);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/page")
    public CommonResult<CommonPage<BroadcastTableVO>> page(@RequestBody @Validated BroadcastTablePageQuery pageQuery) {
        return CommonResult.successPage(broadcastTableBusinessService.page(pageQuery));
    }

    /**
     * 列表
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/list")
    public CommonResult<List<BroadcastTableVO>> list(@RequestBody @Validated BroadcastTablePageQuery pageQuery) {
        pageQuery.setPageNum(1L);
        pageQuery.setPageSize(200L);
        IPage<BroadcastTableVO> page = broadcastTableBusinessService.page(pageQuery);
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
        broadcastTableBusinessService.delete(Lists.newArrayList(id));
        return CommonResult.success(true);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<BroadcastTableVO> detail(@PathVariable("id") Long id) {
        return CommonResult.success(broadcastTableBusinessService.queryDetailById(id));
    } 
 
}
