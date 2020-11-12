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
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    @Override
    public int saveSpuInfo(PmsProductInfo pmsProductInfo) {
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        Integer productId = pmsProductInfo.getId();
        List<PmsProductImage> pmsProductImageList = pmsProductInfo.getPmsProductImageList();
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductInfo.getPmsProductSaleAttrList();
        for (PmsProductImage pmsProductImage : pmsProductImageList) {
            pmsProductImage.setProductId(productId);
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrList) {
            pmsProductSaleAttr.setProductId(productId);
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = pmsProductSaleAttr.getPmsProductSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : pmsProductSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(productId);
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }
        }
        return 1;
    }

    @Override
    public int deleteSpuById(Integer id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setId(id);
        return pmsProductInfoMapper.delete(pmsProductInfo);
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(Integer productId) {
        //根据当前Sku对象的productId现货到该对象所属的spu对象的销售属性
        PmsProductSaleAttr pmsProductSaleAttr=new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(productId);
        List<PmsProductSaleAttr>pmsProductSaleAttrList=pmsProductSaleAttrMapper.select(pmsProductSaleAttr);
        //之后再根据销售属性的Id获取到该销售属性所包含的销售属性值
        for(PmsProductSaleAttr pmsProductSaleAttr1:pmsProductSaleAttrList){
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(productId);
            pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList=pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            pmsProductSaleAttr1.setPmsProductSaleAttrValueList(pmsProductSaleAttrValueList);
        }
        return pmsProductSaleAttrList;
    }

}
