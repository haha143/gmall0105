package com.auguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.PmsProductSaleAttr;
import com.auguigu.gmall.service.PmsProductSaleAttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class PmsProductSaleAttrController {
    @Reference
    PmsProductSaleAttrService pmsProductSaleAttrService;

    @RequestMapping("/spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(@RequestParam Integer spuId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductSaleAttrService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrList;
    }
}
