package com.yuge.ing.shardingsphere.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

/**
 * @author: yuge
 * @since 2022/10/8
 **/
@Setter
public class PageQuery {

    protected static final Long DEFAULT_PAGE_NO = 1L;

    protected static final Long DEFAULT_PAGE_SIZE = 10L;

    /**
     * 当前页
     */
    private Long pageNum;

    /**
     * 每页条目数
     */
    private Long pageSize;

    /**
     * 排序字段
     */
    @Getter
    private List<OrderItem> orders;

    public Long getPageNum() {
        return Optional.ofNullable(pageNum).orElse(DEFAULT_PAGE_NO);
    }

    public Long getPageSize() {
        return Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);
    }
}
