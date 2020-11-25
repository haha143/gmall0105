package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.PmsBaseSaleAttr;
import com.auguigu.gmall.manage.mapper.*;
import com.auguigu.gmall.service.PmsBaseSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PmsBaseSaleAttrImpl implements PmsBaseSaleAttrService {
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }
}
