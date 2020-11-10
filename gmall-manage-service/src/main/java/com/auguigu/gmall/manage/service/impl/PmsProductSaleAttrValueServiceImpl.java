package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.PmsProductSaleAttrValue;
import com.auguigu.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.auguigu.gmall.service.PmsProductSaleAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PmsProductSaleAttrValueServiceImpl implements PmsProductSaleAttrValueService {
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;
    @Override
    public int deleteByProductId(Integer id) {
        PmsProductSaleAttrValue pmsProductSaleAttrValue=new PmsProductSaleAttrValue();
        pmsProductSaleAttrValue.setProductId(id);
        return pmsProductSaleAttrValueMapper.delete(pmsProductSaleAttrValue);
    }
}
