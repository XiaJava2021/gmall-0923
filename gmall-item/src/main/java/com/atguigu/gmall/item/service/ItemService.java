package com.atguigu.gmall.item.service;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.item.feign.GmallPmsClient;
import com.atguigu.gmall.item.feign.GmallSmsClient;
import com.atguigu.gmall.item.feign.GmallWmsClient;
import com.atguigu.gmall.item.vo.ItemVo;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import com.atguigu.gmall.sms.vo.ItemSaleVo;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Hasee  2021/3/23 21:25 周二
 * @version JDK 8.017
 * @description:
 */
@Service
public class ItemService {

    @Autowired
    private GmallSmsClient smsClient;

    @Autowired
    private GmallPmsClient pmsClient;

    @Autowired
    private GmallWmsClient wmsClient;


    public ItemVo loadData(Long skuId) throws Exception {
        ItemVo itemVo = new ItemVo();

        // 1.根据skuId查询sku
        ResponseVo<SkuEntity> skuEntityResponseVo = this.pmsClient.querySkuById(skuId);
        SkuEntity skuEntity = skuEntityResponseVo.getData();
        if (skuEntity == null){
            throw new Exception("您访问的商品不存在.");
        }
        itemVo.setSkuId(skuEntity.getId());
        itemVo.setTitle(skuEntity.getTitle());
        itemVo.setSubTitle(skuEntity.getSubtitle());
        itemVo.setPrice(skuEntity.getPrice());
        itemVo.setWeight(skuEntity.getWeight());
        itemVo.setDefaultImage(skuEntity.getDefaultImage());

        // 2.根据三级分类的id查询一二三级分类
        ResponseVo<List<CategoryEntity>> catesResponseVo = this.pmsClient.queryLvlAllCategoriesByCid3(skuEntity.getCategoryId());
        List<CategoryEntity> categoryEntities = catesResponseVo.getData();
        itemVo.setCategories(categoryEntities);

        // 3.根据品牌id查询品牌
        ResponseVo<BrandEntity> brandEntityResponseVo = this.pmsClient.queryBrandById(skuEntity.getBrandId());
        BrandEntity brandEntity = brandEntityResponseVo.getData();
        if (brandEntity != null){
            itemVo.setBrandId(brandEntity.getId());
            itemVo.setBrandName(brandEntity.getName());
        }

        // 4.根据spuId查询spu
        ResponseVo<SpuEntity> spuEntityResponseVo = this.pmsClient.querySpuById(skuEntity.getSpuId());
        SpuEntity spuEntity = spuEntityResponseVo.getData();
        if (spuEntity != null){
            itemVo.setSpuId(spuEntity.getId());
            itemVo.setSpuName(spuEntity.getName());
        }


        // 5.根据skuId查询营销信息（sms）
        ResponseVo<List<ItemSaleVo>> salesResponseVo = this.smsClient.querySalesBySkuId(skuId);
        List<ItemSaleVo> itemSaleVos = salesResponseVo.getData();
        itemVo.setSales(itemSaleVos);

        // 6.根据skuId查询商品库存信息
        ResponseVo<List<WareSkuEntity>> wareResponseVo = this.wmsClient.queryWareSkuBySkuId(skuId);
        List<WareSkuEntity> wareSkuEntities = wareResponseVo.getData();
        if (!CollectionUtils.isEmpty(wareSkuEntities)){
            itemVo.setStore(wareSkuEntities.stream().anyMatch(wareSkuEntity -> wareSkuEntity.getStock() - wareSkuEntity.getStockLocked() > 0));
        }

        // 7.根据skuId查询sku的图片列表
        ResponseVo<List<SkuImagesEntity>> imagesResponseVo = this.pmsClient.queryImagesBySkuId(skuId);
        List<SkuImagesEntity> imagesEntities = imagesResponseVo.getData();
        itemVo.setImages(imagesEntities);

        // 8.根据spuId查询所有Sku的销售属性
        ResponseVo<List<SaleAttrValueVo>> saleResponseVo = this.pmsClient.querySaleAttrsBySpuId(skuEntity.getSpuId());
        List<SaleAttrValueVo> saleAttrValueVos = saleResponseVo.getData();
        itemVo.setSaleAttrs(saleAttrValueVos);

        // 9.根据skuId查询当前sku的销售属性
        ResponseVo<List<SkuAttrValueEntity>> saleAttrResponseVo = this.pmsClient.querySaleAttrValueBySkuId(skuId);
        List<SkuAttrValueEntity> skuAttrValueEntities = saleAttrResponseVo.getData();
        if (!CollectionUtils.isEmpty(skuAttrValueEntities)){
          itemVo.setSaleAttr(JSON.toJSONString(skuAttrValueEntities.stream().collect(Collectors.toMap(SkuAttrValueEntity::getAttrId,SkuAttrValueEntity::getAttrValue))));
        }


        // 10.根据spuId查询spu下所有销售属性组合和skuId的映射关系
        ResponseVo<Map<String, Object>> mapResponseVo = this.pmsClient.querySalesAttrsMappingSkuIdBySpuId(skuEntity.getSpuId());
        Map<String, Object> map = mapResponseVo.getData();
        itemVo.setSkuJsons(map);

        // 11.根据spuId查询描述信息
        ResponseVo<SpuDescEntity> spuDescEntityResponseVo = this.pmsClient.querySpuDescById(skuEntity.getSpuId());
        SpuDescEntity descEntity = spuDescEntityResponseVo.getData();
        if (descEntity != null && StringUtils.isNotBlank(descEntity.getDecript())) {
            itemVo.setSpuImages(Arrays.asList(StringUtils.split(descEntity.getDecript(),",")));
        }

        // 12.根据分类Id、spuId、skuId查询分组及组下规格参数和值
        ResponseVo<List<ItemGroupVo>> groupResponseVo = this.pmsClient.queryGroupWithAttrValuesByCidAndSpuIdAndSkuId(skuEntity.getCategoryId(), skuEntity.getSpuId(), skuId);
        List<ItemGroupVo> itemGroups = groupResponseVo.getData();
        itemVo.setGroups(itemGroups);

        return itemVo;
    }
}
