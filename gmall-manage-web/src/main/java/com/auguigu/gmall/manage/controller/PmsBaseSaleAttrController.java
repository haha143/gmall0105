package com.auguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.PmsBaseSaleAttr;
import com.auguigu.gmall.bean.PmsProductInfo;
import com.auguigu.gmall.service.PmsBaseSaleAttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class PmsBaseSaleAttrController {
    @Reference
    PmsBaseSaleAttrService pmsBaseSaleAttrService;
    @RequestMapping("/baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrList= pmsBaseSaleAttrService.baseSaleAttrList();
        return pmsBaseSaleAttrList;
    }
}
