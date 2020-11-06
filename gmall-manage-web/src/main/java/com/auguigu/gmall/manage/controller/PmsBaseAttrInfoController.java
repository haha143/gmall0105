package com.auguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.PmsBaseAttrInfo;
import com.auguigu.gmall.bean.PmsBaseAttrValue;
import com.auguigu.gmall.service.PmsBaseAttrInfoService;
import com.auguigu.gmall.service.PmsBaseAttrValueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class PmsBaseAttrInfoController {
    @Reference
    PmsBaseAttrInfoService pmsBaseAttrInfoService;

    @Reference
    PmsBaseAttrValueService pmsBaseAttrValueService;

    @RequestMapping("/attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> getCatalog3(@RequestParam String  catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList= pmsBaseAttrInfoService.getCatalog3(catalog3Id);
        return pmsBaseAttrInfoList;
    }


    @RequestMapping("/saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo  pmsBaseAttrInfo){
        pmsBaseAttrInfoService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    }
}