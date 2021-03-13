package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SpuAttrValueEntity;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;



public class SpuAttrValueVo extends SpuAttrValueEntity {

    public void setValueSelected(List<String> valueSelected) {
        if (CollectionUtils.isEmpty(valueSelected)){
            return;
        }

        // 将 spu属性信息 包装成数组存储
        this.setAttrValue(StringUtils.join(valueSelected,","));
        // 当属性信息是唯一值时,选用索引第一位方法
//        this.setAttrValue(valueSelected.get(0));
    }
}
