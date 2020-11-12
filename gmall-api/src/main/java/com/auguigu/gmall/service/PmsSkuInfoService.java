package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsProductSaleAttr;
import com.auguigu.gmall.bean.PmsSkuInfo;

import java.util.List;

public interface PmsSkuInfoService {
    int saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo selectBySkuId(Integer skuId);

//    List<PmsProductSaleAttr> setCheckedSaleAttrValue(PmsSkuInfo pmsSkuInfo, List<PmsProductSaleAttr> pmsProductSaleAttrList);
}
