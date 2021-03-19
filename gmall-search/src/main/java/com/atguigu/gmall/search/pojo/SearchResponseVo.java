package com.atguigu.gmall.search.pojo;

import com.atguigu.gmall.pms.entity.BrandEntity;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Hasee  2021/3/16 14:14 周二
 * @version JDK 8.017
 * @description:
 */
@Data
public class SearchResponseVo {

    // 品牌过滤条件
    private List<BrandEntity> brands;

    // 分类过滤条件
    private List<CategoryEntity> categories;

    // 规格参数过滤条件
    private List<SearchResponseAttrVo> filters;

    // 分页参数:
    // 当前页回显信息
    private Integer pageNum;
    private Integer pageSize;
    // 总记录数
    private Long total;

    // 商品列表
    private List<Goods> goodsList;
}
