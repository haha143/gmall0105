package com.auguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.PmsProductInfo;
import com.auguigu.gmall.bean.PmsSkuInfo;
import com.auguigu.gmall.service.PmsSkuInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class PmsSkuInfoController {
    @Reference
    PmsSkuInfoService pmsSkuInfoService;

    @RequestMapping("/saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        pmsSkuInfoService.saveSkuInfo(pmsSkuInfo);
        return "insert success";
    }
}
