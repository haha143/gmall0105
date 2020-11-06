package com.auguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.PmsBaseAttrValue;
import com.auguigu.gmall.service.PmsBaseAttrValueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class PmsBaseAttrValueController {
    @Reference
    PmsBaseAttrValueService pmsBaseAttrValueService;

    @RequestMapping("/getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(@RequestParam String  attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValueList= pmsBaseAttrValueService.getAttrValueList(attrId);
        return pmsBaseAttrValueList;
    }

    @RequestMapping("/deleteAttrValueById")
    @ResponseBody
    public String  deleteAttrValueById(@RequestParam String  id){
        PmsBaseAttrValue pmsBaseAttrValue=new PmsBaseAttrValue();
        pmsBaseAttrValue.setId(id);
        pmsBaseAttrValueService.deleteAttrValueById(id);
        return "delete success";
    }

}
