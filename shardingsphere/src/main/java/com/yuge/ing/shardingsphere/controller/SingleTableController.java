package com.yuge.ing.shardingsphere.controller;

import com.yuge.ing.shardingsphere.business.SingleTableBusinessService;
import com.yuge.ing.shardingsphere.core.CommonPage;
import com.yuge.ing.shardingsphere.core.CommonResult;
import com.yuge.ing.shardingsphere.dto.SingleTableDTO;
import com.yuge.ing.shardingsphere.dto.SingleTablePageQuery;
import com.yuge.ing.shardingsphere.vo.SingleTableVO;
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
 * single table
 *
 * @author yuge
 * @since 2024-04-02
 */
@RestController
@RequestMapping("/singleTable")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SingleTableController {

    private final SingleTableBusinessService singleTableBusinessService;

    /**
     * 新增
     *
     * @param singleTableDTO
     * @return
     */
    @PostMapping
    public CommonResult<Long> add(@RequestBody @Validated SingleTableDTO singleTableDTO) {
        return CommonResult.success(singleTableBusinessService.add(singleTableDTO));
    }

    /**
     * 编辑
     *
     * @param singleTableDTO
     * @return
     */
    @PutMapping("/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id,
                                      @RequestBody @Validated SingleTableDTO singleTableDTO) {
        singleTableDTO.setId(id);
        singleTableBusinessService.edit(singleTableDTO);
        return CommonResult.success(true);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/page")
    public CommonResult<CommonPage<SingleTableVO>> page(@RequestBody @Validated SingleTablePageQuery pageQuery) {
        return CommonResult.successPage(singleTableBusinessService.page(pageQuery));
    }

    /**
     * 列表
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/list")
    public CommonResult<List<SingleTableVO>> list(@RequestBody @Validated SingleTablePageQuery pageQuery) {
        pageQuery.setPageNum(1L);
        pageQuery.setPageSize(200L);
        IPage<SingleTableVO> page = singleTableBusinessService.page(pageQuery);
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
        singleTableBusinessService.delete(Lists.newArrayList(id));
        return CommonResult.success(true);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<SingleTableVO> detail(@PathVariable("id") Long id) {
        return CommonResult.success(singleTableBusinessService.queryDetailById(id));
    } 
 
}
