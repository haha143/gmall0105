package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsProductImage;

import java.util.List;

public interface PmsProductImageService {
    int deleteByProductId(Integer id);

    List<PmsProductImage> spuImageList(Integer spuId);
}
