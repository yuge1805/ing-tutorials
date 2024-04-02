package com.yuge.ing.shardingsphere.condition;

import com.yuge.ing.shardingsphere.dto.PageQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 *
 * @author yuge
 * @since 2024-04-02
 */
@Getter
@Setter
public class SingleTableCondition extends PageQuery {

    private Long id;

    private String name;

    private String status;

}
