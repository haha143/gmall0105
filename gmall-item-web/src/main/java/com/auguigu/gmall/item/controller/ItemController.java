package com.auguigu.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.auguigu.gmall.bean.PmsProductSaleAttr;
import com.auguigu.gmall.bean.PmsSkuInfo;
import com.auguigu.gmall.bean.PmsSkuSaleAttrValue;
import com.auguigu.gmall.service.PmsProductInfoService;
import com.auguigu.gmall.service.PmsSkuInfoService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {
    @Reference
    PmsSkuInfoService pmsSkuInfoService;

    @Reference
    PmsProductInfoService pmsProductInfoService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable Integer skuId,ModelMap modelMap){
        PmsSkuInfo pmsSkuInfo=pmsSkuInfoService.selectBySkuId(skuId);
        List<PmsProductSaleAttr>pmsProductSaleAttrList=pmsProductInfoService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),skuId);
//        pmsProductSaleAttrList=pmsSkuInfoService.setCheckedSaleAttrValue(pmsSkuInfo,pmsProductSaleAttrList);
        //sku对象
        modelMap.put("skuInfo",pmsSkuInfo);
        //销售属性列表
        modelMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrList);
        //创建该SKU对象对应的SPU对象下的所有SKU对象的一个hash表
        Map<Object,Object>SkuInfoHashmap= new HashedMap();
        List<PmsSkuInfo>pmsSkuInfoList=pmsSkuInfoService.getSkuInfosBySpu(pmsSkuInfo.getProductId());
        for(PmsSkuInfo pmsSkuInfo1:pmsSkuInfoList){
            String k="";
            Integer v=pmsSkuInfo1.getId();
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList=pmsSkuInfo1.getPmsSkuSaleAttrValueList();
            for(PmsSkuSaleAttrValue pmsSkuSaleAttrValue:skuSaleAttrValueList){
                k+=pmsSkuSaleAttrValue.getSaleAttrValueId()+"|";
            }
            SkuInfoHashmap.put(k,v);
        }
        //将该hash表传给前端,并且一直存放在前端
        String SkuInfoHashMapjsonStr = JSON.toJSONString(SkuInfoHashmap);
        modelMap.put("valueSku",SkuInfoHashMapjsonStr);
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
