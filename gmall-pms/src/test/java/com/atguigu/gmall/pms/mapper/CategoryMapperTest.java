package com.atguigu.gmall.pms.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hasee  2021/3/20 14:07 周六
 * @version JDK 8.017
 * @description:
 */
@SpringBootTest
class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void queryLvl2CatesWithSubsByPid(){
        System.out.println(this.categoryMapper.queryLvl2CatesWithSubsByPid(1L));
    }
}