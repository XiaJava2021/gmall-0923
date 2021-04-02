package com.atguigu.gmall.item.vo;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.entity.SkuImagesEntity;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import com.atguigu.gmall.sms.vo.ItemSaleVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Hasee  2021/3/23 16:43 周二
 * @version JDK 8.017
 * @description:
 */
@Data
public class ItemVo {

    // 面包屑: 一二三级分类
    private List<CategoryEntity> categories;

    // 面包屑: 品牌信息
    private Long brandId;
    private String brandName;

    // 面包屑: spu 信息
    private Long spuId;
    private String spuName;

    // sku 相关信息
    private Long skuId;
    private String title;
    private String subTitle;
    private BigDecimal price;
    private Integer weight;
    private String defaultImage;

    // 商品营销信息
    private List<ItemSaleVo> sales;

    // 是否有货 默认无货
    private Boolean store = false;

    // 图片列表
    private List<SkuImagesEntity> images;

    // 销售属性列表: [{attrId:3,attrName: 颜色, attrValues:{'黑色','白色'}},
    // {attrId:4,attrName: 内存, attrValues:{'8G','12G'}},
    // {attrId:5,attrName: 机身存储, attrValues:{'128G','256G','512G'}}]
    private List<SaleAttrValueVo> saleAttrs;

    // 当前商品的销售属性 JSON 格式: {3: '黑色',4: '8G',5: '512G'}
    private Map<Long,String> saleAttr;

    // 销售属性组合和 skuId 的映射关系: ['黑色,8G,128G': 10, '黑色,12G,256G': 11]
    private Map<String,Object> skuJsons;

    // 商品描述
    private List<String> spuImages;

    // 规格参数
    private List<ItemGroupVo> groups;
}
