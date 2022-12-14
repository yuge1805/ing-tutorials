package com.yuge.ing.mybatisplus.service.impl;

import com.yuge.ing.mybatisplus.entity.BaseOrg;
import com.yuge.ing.mybatisplus.enums.RegionEnum;
import com.yuge.ing.mybatisplus.service.IBaseOrgService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BaseOrgServiceImplTest {

    @Resource
    private IBaseOrgService baseOrgService;

    @Test
    void save() {
        BaseOrg baseOrg = new BaseOrg();
        baseOrg.setName("xxx Org");
        baseOrg.setRegion(RegionEnum.XIAN);
        baseOrgService.save(baseOrg);
    }
}