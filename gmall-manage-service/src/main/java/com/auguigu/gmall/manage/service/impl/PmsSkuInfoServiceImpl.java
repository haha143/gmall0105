package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.*;
import com.auguigu.gmall.manage.mapper.*;
import com.auguigu.gmall.service.PmsSkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PmsSkuInfoServiceImpl implements PmsSkuInfoService {
    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Override
    public int saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //当用户没有选择默认图片是，自动将图片组里的第一张图片设置为默认图片
        if (pmsSkuInfo.getSkuDefaultImg() == null) {
            pmsSkuInfo.setSkuDefaultImg(pmsSkuInfo.getPmsSkuImageList().get(0).getImgUrl());
            pmsSkuInfo.getPmsSkuImageList().get(0).setIsDefault("1");
        }
        //插入sku对象
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        int skuId = pmsSkuInfo.getId();
        List<PmsSkuImage> pmsSkuImageList = pmsSkuInfo.getPmsSkuImageList();
        List<PmsSkuAttrValue> pmsSkuAttrValueList = pmsSkuInfo.getPmsSkuAttrValueList();
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList = pmsSkuInfo.getPmsSkuSaleAttrValueList();
        //插入sku图片对象
        for (PmsSkuImage pmsSkuImage : pmsSkuImageList) {
            //设置sku图片的相关联的skuId
            pmsSkuImage.setSkuId(skuId);
            //设置sku图片相关联的productId
            PmsProductImage pmsProductImage = new PmsProductImage();
            pmsProductImage.setImgUrl(pmsSkuImage.getImgUrl());
            PmsProductImage pmsProductImage1 = pmsProductImageMapper.selectOne(pmsProductImage);
            pmsSkuImage.setProductImgId(pmsProductImage1.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }
        //插入sku平台属性对象
        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        //插图sku销售属性对象
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
        return 1;
    }

    @Override
    public PmsSkuInfo selectBySkuId(Integer skuId) {
        PmsSkuInfo pmsSkuInfo=new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo pmsSkuInfo1=pmsSkuInfoMapper.selectOne(pmsSkuInfo);
        //sku的图片集合
        PmsSkuImage pmsSkuImage=new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImageList=pmsSkuImageMapper.select(pmsSkuImage);
        pmsSkuInfo1.setPmsSkuImageList(pmsSkuImageList);
        //平台属性
        PmsSkuAttrValue pmsSkuAttrValue=new PmsSkuAttrValue();
        pmsSkuAttrValue.setSkuId(skuId);
        List<PmsSkuAttrValue>pmsSkuAttrValueList=pmsSkuAttrValueMapper.select(pmsSkuAttrValue);
        pmsSkuInfo1.setPmsSkuAttrValueList(pmsSkuAttrValueList);
        //销售属性
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue=new PmsSkuSaleAttrValue();
        pmsSkuSaleAttrValue.setSkuId(skuId);
        List<PmsSkuSaleAttrValue>pmsSkuSaleAttrValueList=pmsSkuSaleAttrValueMapper.select(pmsSkuSaleAttrValue);
        pmsSkuInfo1.setPmsSkuSaleAttrValueList(pmsSkuSaleAttrValueList);
        return pmsSkuInfo1;
    }

//    @Override
//    public List<PmsProductSaleAttr> setCheckedSaleAttrValue(PmsSkuInfo pmsSkuInfo, List<PmsProductSaleAttr> pmsProductSaleAttrList) {
//        List<PmsSkuSaleAttrValue>pmsSkuSaleAttrValueList=pmsSkuInfo.getPmsSkuSaleAttrValueList();
//        for(PmsSkuSaleAttrValue pmsSkuSaleAttrValue:pmsSkuSaleAttrValueList){
//            for(PmsProductSaleAttr pmsProductSaleAttr:pmsProductSaleAttrList){
//                for(PmsProductSaleAttrValue pmsProductSaleAttrValue:pmsProductSaleAttr.getPmsProductSaleAttrValueList()){
//                    if(pmsSkuSaleAttrValue.getSaleAttrValueId()==pmsProductSaleAttrValue.getId()){
//                        pmsProductSaleAttrValue.setIsChecked();
//                    }
//                }
//            }
//        }
//        return null;
//    }
}
