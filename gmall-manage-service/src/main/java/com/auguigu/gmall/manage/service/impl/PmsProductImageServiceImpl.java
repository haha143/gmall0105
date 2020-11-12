package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.PmsProductImage;
import com.auguigu.gmall.manage.mapper.PmsProductImageMapper;
import com.auguigu.gmall.service.PmsProductImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PmsProductImageServiceImpl implements PmsProductImageService {
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Override
    public int deleteByProductId(Integer id) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(id);
        return pmsProductImageMapper.delete(pmsProductImage);
    }

    @Override
    public List<PmsProductImage> spuImageList(Integer spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        return pmsProductImageMapper.select(pmsProductImage);
    }
}
