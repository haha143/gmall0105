package com.auguigu.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.PmsProductInfo;
import com.auguigu.gmall.bean.PmsProductSaleAttr;
import com.auguigu.gmall.bean.PmsProductSaleAttrValue;
import com.auguigu.gmall.bean.PmsSkuInfo;
import com.auguigu.gmall.service.PmsProductInfoService;
import com.auguigu.gmall.service.PmsProductSaleAttrValueService;
import com.auguigu.gmall.service.PmsSkuInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {
    @Reference
    PmsSkuInfoService pmsSkuInfoService;

    @Reference
    PmsProductInfoService pmsProductInfoService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable Integer skuId,ModelMap modelMap){
        PmsSkuInfo pmsSkuInfo=pmsSkuInfoService.selectBySkuId(skuId);
        List<PmsProductSaleAttr>pmsProductSaleAttrList=pmsProductInfoService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId());
//        pmsProductSaleAttrList=pmsSkuInfoService.setCheckedSaleAttrValue(pmsSkuInfo,pmsProductSaleAttrList);
        //sku对象
        modelMap.put("skuInfo",pmsSkuInfo);
        //销售属性列表
        modelMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrList);
        return "item";
    }



//  测试方法
    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        List<String>list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add("循环数据"+i);
        }
        modelMap.put("hello","are you ok");
        modelMap.put("list",list);
        modelMap.put("check",1);
        return "index";
    }
}
