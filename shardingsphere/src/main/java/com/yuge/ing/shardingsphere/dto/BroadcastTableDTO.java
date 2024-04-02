package com.yuge.ing.shardingsphere.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class BroadcastTableDTO {

    @JsonIgnore
    private Long id;

    private String name;

    private String status;

}
