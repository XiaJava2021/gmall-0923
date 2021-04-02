package com.atguigu.gmall.item.controller;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.item.vo.ItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hasee  2021/3/23 21:23 周二
 * @version JDK 8.017
 * @description:
 */

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("{skuId}.html")
    public String loadData(@PathVariable("skuId")Long skuId, Model model) throws Exception {
        System.out.println("================");
        ItemVo itemVo = this.itemService.loadData(skuId);
        model.addAttribute("itemVo",itemVo);
        this.itemService.createHtml(itemVo);
        return "item";
    }

}
