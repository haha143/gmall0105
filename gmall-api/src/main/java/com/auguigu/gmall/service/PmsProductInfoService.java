package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsProductInfo;

import java.util.List;

public interface PmsProductInfoService {
    List<PmsProductInfo>spuList(String catalog3Id);

    int saveSpuInfo(PmsProductInfo pmsProductInfo);
}
