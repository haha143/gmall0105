package com.auguigu.gmall.manage.service.impl;

import com.auguigu.gmall.bean.PmsSkuInfo;
import com.auguigu.gmall.manage.mapper.PmsSkuInfoMapper;
import com.auguigu.gmall.service.PmsSkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;

public class PmsSkuInfoServiceImpl implements PmsSkuInfoService {
    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Override
    public int saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        return pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
    }
}
