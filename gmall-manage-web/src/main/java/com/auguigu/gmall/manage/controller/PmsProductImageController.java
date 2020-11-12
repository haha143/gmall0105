package com.auguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.PmsProductImage;
import com.auguigu.gmall.service.PmsProductImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class PmsProductImageController {
    @Reference
    PmsProductImageService pmsProductImageService;

    @RequestMapping("/spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(@RequestParam Integer spuId) {
        List<PmsProductImage> pmsProductImageList = pmsProductImageService.spuImageList(spuId);
        return pmsProductImageList;
    }
}
