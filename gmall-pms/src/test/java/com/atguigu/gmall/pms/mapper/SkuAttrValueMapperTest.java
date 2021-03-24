package com.atguigu.gmall.pms.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hasee  2021/3/23 20:32 周二
 * @version JDK 8.017
 * @description:
 */
@SpringBootTest
class SkuAttrValueMapperTest {

    @Autowired
    private SkuAttrValueMapper attrValueMapper;

    @Test
    void querySaleAttrsMappingSkuIdBySkuIds() {
        System.out.println(this.attrValueMapper.querySaleAttrsMappingSkuIdBySkuIds(Arrays.asList(27L, 28L, 29L, 30L)));

    }
}