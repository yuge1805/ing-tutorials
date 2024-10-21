package com.yuge.ing.spring.controller;

import com.yuge.ing.spring.annotation.Decrypt;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yuge
 * @date: 2024/10/10
 **/
@RestController
public class DecryptController {

    /**
     * 支持请求：/path-var-decrypt/d5381d8fc3c081d85ac46e303f7223b8
     *
     * @param id
     * @return
     */
    @RequestMapping("/path-var-decrypt/{id}")
    public String pathVarDecrypt(@PathVariable("id") @Decrypt Long id) {
        return String.valueOf(id);
    }

    /**
     * 支持请求：/request-param-decrypt?id=d5381d8fc3c081d85ac46e303f7223b8
     *
     * @param id
     * @return
     */
    @GetMapping("/request-param-decrypt")
    public String requestParamDecrypt(@RequestParam("id") @Decrypt Long id) {
        return String.valueOf(id);
    }

    /**
     * 支持请求：/request-param-list-decrypt?ids=d5381d8fc3c081d85ac46e303f7223b8,d5381d8fc3c081d85ac46e303f7223b8
     *
     * @param ids
     * @return
     */
    @GetMapping("/request-param-list-decrypt")
    public String requestParamListDecrypt(@RequestParam("ids") @Decrypt List<Long> ids) {
        return String.valueOf(ids);
    }

    /**
     * 支持请求：/request-param-array-decrypt?ids=d5381d8fc3c081d85ac46e303f7223b8,d5381d8fc3c081d85ac46e303f7223b8
     *
     * @param ids
     * @return
     */
    @GetMapping("/request-param-array-decrypt")
    public String requestParamArrayDecrypt(@RequestParam("ids") @Decrypt long[] ids) {
        return String.valueOf(Arrays.stream(ids).boxed().collect(Collectors.toList()));
    }

    /**
     * 支持请求：/request-body-list-decrypt     requestBody: ["d5381d8fc3c081d85ac46e303f7223b8", "d5381d8fc3c081d85ac46e303f7223b8"]
     *
     * @param ids
     * @return
     */
    @PostMapping("/request-body-list-decrypt")
    public String requestBodyListDecrypt(@RequestBody @Decrypt List<Long> ids) {
        return String.valueOf(ids);
    }

    /**
     * 暂不支持数组形式，后续需要再说
     *
     * @param ids
     * @return
     */
    @PostMapping("/request-body-array-decrypt")
    public String requestBodyArrayDecrypt(@RequestBody long[] ids) {
        return String.valueOf(Arrays.stream(ids).boxed().collect(Collectors.toList()));
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        e.printStackTrace();
        return "error";
    }

}
