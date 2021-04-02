package com.atguigu.gmall.oms.vo;

import com.atguigu.gmall.ums.entity.UserAddressEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Hasee  2021/3/31 16:46 周三
 * @version JDK 8.017
 * @description:
 */
@Data
public class OrderSubmitVo {

    // 防重的唯一标识
    private String orderToken;

    // 用户选中的收货地址
    private UserAddressEntity address;

    // 支付方式
    private Integer payType;

    // 配送方式、快递公司
    private String deliveryCompany;

    // 消费购买积分
    private Integer bounds;

    // 总价: 验总价
    private BigDecimal totalPrice;

    // 送货列表清单
    private List<OrderItemVo> items;
}
