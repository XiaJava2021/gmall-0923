package com.atguigu.gmall.payment.controller;

import com.atguigu.gmall.common.exception.OrderException;
import com.atguigu.gmall.oms.entity.OrderEntity;
import com.atguigu.gmall.payment.interceptors.LoginInterceptor;
import com.atguigu.gmall.payment.pojo.UserInfo;
import com.atguigu.gmall.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Hasee  2021/4/1 19:44 周四
 * @version JDK 8.017
 * @description:
 */
@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping("pay.html")
    public String paySelect(@RequestParam("orderToken")String orderToken, Model model){
        OrderEntity orderEntity = this.paymentService.queryOrderByToken(orderToken);
        if (orderEntity  == null){
            throw new OrderException("参数错误,您支付的订单不存在.");
        }
        // 通过拦截器获取当前用户登陆状态
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        // 判断订单中的用户是否是当前用户
        if (orderEntity.getUserId() != userInfo.getUserId()){
            throw new OrderException("这个订单不属于您.");
        }

        // 判断订单状态是否是待付款
        if (orderEntity.getStatus() != 0){
            throw new OrderException("这个订单无法支付...");
        }

        model.addAttribute("orderEntity",orderEntity);

        return "pay";
    }
}
