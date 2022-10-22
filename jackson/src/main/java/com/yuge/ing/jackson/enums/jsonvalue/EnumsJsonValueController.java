package com.yuge.ing.jackson.enums.jsonvalue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuge
 * @date: 2022/10/22
 **/
@Slf4j
@RestController
@RequestMapping("/enums/jsonvalue")
public class EnumsJsonValueController {

    @PostMapping
    public Org add(@RequestBody Org org) {
        log.info(org.toString());
        return org;
    }

}
