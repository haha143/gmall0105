package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.PmsProductInfo;
import com.auguigu.gmall.manage.mapper.PmsProductInfoMapper;
import com.auguigu.gmall.service.PmsProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class PmsProductInfoServiceImpl implements PmsProductInfoService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo=new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    @Override
    public int saveSpuInfo(PmsProductInfo pmsProductInfo) {
        return pmsProductInfoMapper.insertSelective(pmsProductInfo);
    }
}
