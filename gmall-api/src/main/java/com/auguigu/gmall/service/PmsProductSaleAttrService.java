package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface PmsProductSaleAttrService {
    int deleteByProductId(Integer id);

    List<PmsProductSaleAttr> spuSaleAttrList(Integer spuId);
}
