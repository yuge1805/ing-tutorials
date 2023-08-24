package com.yuge.ing.mybatisplus.service.impl;

import com.yuge.ing.mybatisplus.entity.CharacterSetDemo;
import com.yuge.ing.mybatisplus.service.ICharacterSetDemoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author: yuge
 * @date: 2023/8/23
 **/
@SpringBootTest
public class CharacterSetDemoServiceImplTest {

    @Resource
    private ICharacterSetDemoService characterSetDemoService;

    @Test
    void save() {
        CharacterSetDemo demo = new CharacterSetDemo();
        demo.setContent("直木“\uD86D\uDC13以为轮”“木受绳则直，金就砺则利”");
        characterSetDemoService.save(demo);
    }

}
