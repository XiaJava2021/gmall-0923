package com.atguigu.gmall.cart.controller;

import com.atguigu.gmall.cart.interceptors.LoginInterceptor;
import com.atguigu.gmall.cart.pojo.Cart;
import com.atguigu.gmall.cart.service.CartService;
import com.atguigu.gmall.common.bean.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Hasee  2021/3/29 10:51 周一
 * @version JDK 8.017
 * @description:
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 根据用户 id 查询购物车信息
     * @return
     */
    @GetMapping("user/{userId}")
    @ResponseBody
    public ResponseVo<List<Cart>> queryCheckedCarts(@PathVariable("userId")Long userId){
        List<Cart> carts = this.cartService.queryCheckedCarts(userId);
        return ResponseVo.ok(carts);
    }


    @PostMapping("deleteCart")
    @ResponseBody
    public ResponseVo deleteCart(@RequestParam("skuId")Long skuId){
        this.cartService.deleteCart(skuId);
        return ResponseVo.ok();
    }



    @PostMapping("updateNum")
    @ResponseBody
    public ResponseVo updateNum(@RequestBody Cart cart){
        this.cartService.updateNum(cart);
        return ResponseVo.ok();
    }


    /**
     * 查询购物车
     */
    @GetMapping("cart.html")
    public String queryCarts(Model model){
        List<Cart> carts = this.cartService.queryCarts();
        model.addAttribute("carts",carts);
        return "cart";
    }

    @GetMapping
    public String saveCart(Cart cart){
        this.cartService.saveCart(cart);
        return "redirect:http://cart.gmall.com/addCart.html?skuId=" + cart.getSkuId() + "&count=" + cart.getCount();
    }

    @GetMapping("addCart.html")
    public String queryCartBySkuId(
            @RequestParam("skuId")Long skuId,
            @RequestParam("count")Integer count,
            Model model
    ){
        Cart cart = this.cartService.queryCartBySkuId(skuId,count);
        model.addAttribute("cart",cart);
        return "addCart";
    }




    @GetMapping("test")
    @ResponseBody
    public String test(HttpServletRequest request){
//        System.out.println("这是 controller 的测试方法" + LoginInterceptor.userInfo);
//        System.out.println(request.getAttribute("userId") + " = = = " + request.getAttribute("userKey"));
//        System.out.println(LoginInterceptor.getUserInfo());
        long now = System.currentTimeMillis();
        System.out.println("controller 中的异步开始..."  + now);
        ListenableFuture<String> future1 = this.cartService.executor1();
        ListenableFuture<String> future2 = this.cartService.executor2();
//        try {
//            System.out.println("获取到子任务 1 的返回结果集: " + future1.get());
//            System.out.println("获取到子任务 2 的返回结果集: " + future2.get());
//        } catch (Exception e) {
//            System.out.println("捕获到子任务的异常信息: " + e.getMessage());
//        }
//        future1.addCallback(result -> System.out.println("调用成功 future1: " + result), ex -> System.out.println("调用失败 future1 : " + ex.getMessage()));
//        future2.addCallback(result -> System.out.println("调用成功 future2: " + result), ex -> System.out.println("调用失败 future2 : " + ex.getMessage()));
        System.out.println("controller 中的异步执行结束=====" + (System.currentTimeMillis() - now));
        return "hello test";
    }
}
