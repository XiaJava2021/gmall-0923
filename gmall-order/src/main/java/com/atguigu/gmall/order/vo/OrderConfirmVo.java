package com.atguigu.gmall.order.vo;

import com.atguigu.gmall.oms.vo.OrderItemVo;
import com.atguigu.gmall.ums.entity.UserAddressEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmVo {

    // 收货地址列表
    private List<UserAddressEntity> addresses;

    // 送货清单，根据购物车页面传递过来的skuIds查询
    private List<OrderItemVo> orderItems;

    // 用户的购物积分信息，ums_member 表中的 integration 字段
    private Integer bounds;

    // 防重(保证幂等性)的唯一标识
    private String orderToken;
}