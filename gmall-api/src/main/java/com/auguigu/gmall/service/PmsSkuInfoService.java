package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsSkuInfo;

import java.util.List;

public interface PmsSkuInfoService {
    int saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo selectBySkuId(Integer skuId);

    List<PmsSkuInfo> getSkuInfosBySpu(Integer productId);

//    List<PmsProductSaleAttr> setCheckedSaleAttrValue(PmsSkuInfo pmsSkuInfo, List<PmsProductSaleAttr> pmsProductSaleAttrList);
}
