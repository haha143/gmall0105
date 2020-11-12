package com.auguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.PmsProductImage;
import com.auguigu.gmall.bean.PmsProductInfo;
import com.auguigu.gmall.service.PmsProductImageService;
import com.auguigu.gmall.service.PmsProductInfoService;
import com.auguigu.gmall.service.PmsProductSaleAttrService;
import com.auguigu.gmall.service.PmsProductSaleAttrValueService;
import com.auguigu.gmall.util.PmsUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
public class PmsProductInfoController {
    @Reference
    PmsProductInfoService pmsProductInfoService;

    @Reference
    PmsProductImageService pmsProductImageService;

    @Reference
    PmsProductSaleAttrService pmsProductSaleAttrService;

    @Reference
    PmsProductSaleAttrValueService pmsProductSaleAttrValueService;

    //获取所有的商铺信息
    @RequestMapping("/spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(@RequestParam Integer catalog3Id) {
        List<PmsProductInfo> pmsProductInfoList = pmsProductInfoService.spuList(catalog3Id);
        return pmsProductInfoList;
    }

    //保存spu信息
    @RequestMapping("/saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        pmsProductInfoService.saveSpuInfo(pmsProductInfo);
        return "insert success";
    }

    //图片上传
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String imageUrl = PmsUploadUtil.uploadImage(multipartFile);
        //将图片的存储路径返回给页面用于提交源数据
        return imageUrl;
    }

    @RequestMapping("/deleteSpuById")
    @ResponseBody
    public String deleteSpuById(@RequestParam Integer id) {
        //删除该spu及其所属的所有对象
        pmsProductInfoService.deleteSpuById(id);
        pmsProductImageService.deleteByProductId(id);
        pmsProductSaleAttrService.deleteByProductId(id);
        pmsProductSaleAttrValueService.deleteByProductId(id);
        System.out.println(id);
        System.out.println("已经成功删除了");
        return "delete success";
    }
}
