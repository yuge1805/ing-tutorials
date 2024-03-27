package com.yuge.ing.shardingsphere.controller;

import com.yuge.ing.shardingsphere.business.OrderBusinessService;
import com.yuge.ing.shardingsphere.core.CommonPage;
import com.yuge.ing.shardingsphere.core.CommonResult;
import com.yuge.ing.shardingsphere.dto.OrderDTO;
import com.yuge.ing.shardingsphere.dto.OrderPageQuery;
import com.yuge.ing.shardingsphere.vo.OrderVO;
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
 * order
 *
 * @author yuge
 * @since 2024-03-26
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderBusinessService orderBusinessService;

    /**
     * 新增
     *
     * @param orderDTO
     * @return
     */
    @PostMapping
    public CommonResult<Boolean> add(@RequestBody @Validated OrderDTO orderDTO) {
        orderBusinessService.add(orderDTO);
        return CommonResult.success(true);
    }

    /**
     * 编辑
     *
     * @param orderDTO
     * @return
     */
    @PutMapping("/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id,
                                      @RequestBody @Validated OrderDTO orderDTO) {
        orderDTO.setOrderId(id);
        orderBusinessService.edit(orderDTO);
        return CommonResult.success(true);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/page")
    public CommonResult<CommonPage<OrderVO>> page(@RequestBody @Validated OrderPageQuery pageQuery) {
        return CommonResult.successPage(orderBusinessService.page(pageQuery));
    }

    /**
     * 列表
     *
     * @param pageQuery
     * @return
     */
    @PostMapping("/list")
    public CommonResult<List<OrderVO>> list(@RequestBody @Validated OrderPageQuery pageQuery) {
        pageQuery.setPageNum(1L);
        pageQuery.setPageSize(200L);
        IPage<OrderVO> page = orderBusinessService.page(pageQuery);
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
        orderBusinessService.delete(Lists.newArrayList(id));
        return CommonResult.success(true);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<OrderVO> detail(@PathVariable("id") Long id) {
        return CommonResult.success(orderBusinessService.queryDetailById(id));
    } 
 
}
