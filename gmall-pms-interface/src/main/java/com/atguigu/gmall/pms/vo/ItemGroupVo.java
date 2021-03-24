package com.atguigu.gmall.pms.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Hasee  2021/3/23 17:04 周二
 * @version JDK 8.017
 * @description:
 */
@Data
public class ItemGroupVo {

    private Long id;
    private String name;
    private List<AttrValueVo> attrValues;
}
