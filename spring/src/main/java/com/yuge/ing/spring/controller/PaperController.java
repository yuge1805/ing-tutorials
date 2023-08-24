package com.yuge.ing.spring.controller;

import com.yuge.ing.spring.annotation.LoginIgnore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuge
 * @date: 2023/7/5
 **/
@LoginIgnore
@RestController
@RequestMapping("/paper")
public class PaperController {

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/enable")
    public String enable(@RequestParam Long id) {
        return null;
    }

}
