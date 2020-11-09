package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.*;
import com.auguigu.gmall.manage.mapper.PmsProductImageMapper;
import com.auguigu.gmall.manage.mapper.PmsProductInfoMapper;
import com.auguigu.gmall.manage.mapper.PmsProductSaleAttrMapper;
import com.auguigu.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.auguigu.gmall.service.PmsProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class PmsProductInfoServiceImpl implements PmsProductInfoService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(Integer catalog3Id) {
        PmsProductInfo pmsProductInfo=new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    @Override
    public int saveSpuInfo(PmsProductInfo pmsProductInfo) {
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        Integer productId=pmsProductInfo.getId();
        List<PmsProductImage> pmsProductImageList= pmsProductInfo.getPmsProductImageList();
        List<PmsProductSaleAttr> pmsProductSaleAttrList=pmsProductInfo.getPmsProductSaleAttrList();
        for(PmsProductImage pmsProductImage:pmsProductImageList){
            pmsProductImage.setProductId(productId);
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }
        for(PmsProductSaleAttr pmsProductSaleAttr:pmsProductSaleAttrList){
            pmsProductSaleAttr.setProductId(productId);
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
            List<PmsProductSaleAttrValue>pmsProductSaleAttrValueList=pmsProductSaleAttr.getPmsProductSaleAttrValueList();
            for(PmsProductSaleAttrValue pmsProductSaleAttrValue:pmsProductSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(productId);
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }
        }
        return 1;
    }
}
