package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.PmsBaseAttrInfo;
import com.auguigu.gmall.bean.PmsBaseAttrValue;
import com.auguigu.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.auguigu.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.auguigu.gmall.service.PmsBaseAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class PmsBaseAttrInfoServiceImpl implements PmsBaseAttrInfoService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> getCatalog3(Integer catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        for (PmsBaseAttrInfo pmsBaseAttrInfo1 : pmsBaseAttrInfoList) {
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo1.getId());
            List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
            pmsBaseAttrInfo1.setAttrValueList(pmsBaseAttrValueList);
        }
        return pmsBaseAttrInfoList;
    }

    @Override
    public int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrInfo.getAttrValueList();
        //属性id为空，说明为新增属性
        if (pmsBaseAttrInfo.getId() == null) {
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);
        }
        //属性id不为空，说明为修改属性或者是在已经存在的属性里面进行新增或修改属性值的操作
        else {
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id", pmsBaseAttrInfo.getId());
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo, example);
        }
        //先将所有该属性下的属性值全部删除
        PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
        pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
        pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDel);
        //循环将属性值列表的值插入到属性值表中
        for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrValueList) {
            pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
        }
        return 1;
    }

    @Override
    public int deleteAttrInfoById(Integer id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setId(id);
        return pmsBaseAttrInfoMapper.delete(pmsBaseAttrInfo);
    }


}
