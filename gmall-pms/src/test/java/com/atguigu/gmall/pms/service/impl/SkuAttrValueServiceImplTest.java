package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.service.SkuAttrValueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hasee  2021/3/23 20:46 周二
 * @version JDK 8.017
 * @description:
 */
@SpringBootTest
class SkuAttrValueServiceImplTest {

    @Autowired
    private SkuAttrValueService skuAttrValueService;

    @Test
    void querySalesAttrsMappingSkuIdBySpuId() {
        System.out.println(this.skuAttrValueService.querySalesAttrsMappingSkuIdBySpuId(29L));
    }
}