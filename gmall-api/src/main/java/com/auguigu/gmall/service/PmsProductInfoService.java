package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsProductInfo;

import java.util.List;

public interface PmsProductInfoService {
    List<PmsProductInfo>spuList(Integer catalog3Id);

    int saveSpuInfo(PmsProductInfo pmsProductInfo);

    int deleteSpuById(Integer id);
}
