package com.atguigu.gmall.search.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Hasee  2021/3/16 14:27 周二
 * @version JDK 8.017
 * @description:
 */
@Data
public class SearchResponseAttrVo {

    // 规格参数过滤所需字段
    private Long attrId;
    private String attrName;
    private List<String> attrValues;
}
