package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.PmsBaseAttrValue;
import com.auguigu.gmall.manage.mapper.*;
import com.auguigu.gmall.service.PmsBaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PmsBaseAttrValueServiceImpl implements PmsBaseAttrValueService {
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(Integer attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
        return pmsBaseAttrValueList;
    }

    @Override
    public int deleteAttrValueById(Integer id) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setId(id);
        return pmsBaseAttrValueMapper.delete(pmsBaseAttrValue);
    }

    @Override
    public int deleteAttrValueByAttrId(Integer id) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(id);
        return pmsBaseAttrValueMapper.delete(pmsBaseAttrValue);
    }


}
