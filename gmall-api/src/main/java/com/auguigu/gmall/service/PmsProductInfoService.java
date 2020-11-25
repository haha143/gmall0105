package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsProductInfo;
import com.auguigu.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface PmsProductInfoService {
    List<PmsProductInfo> spuList(Integer catalog3Id);

    int saveSpuInfo(PmsProductInfo pmsProductInfo);

    int deleteSpuById(Integer id);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(Integer productId,Integer skuId);
}
