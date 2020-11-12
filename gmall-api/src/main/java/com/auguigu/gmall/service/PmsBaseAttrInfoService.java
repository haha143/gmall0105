package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsBaseAttrInfo;
import com.auguigu.gmall.bean.PmsBaseAttrValue;

import java.util.List;

public interface PmsBaseAttrInfoService {
    List<PmsBaseAttrInfo> getCatalog3(Integer catalog3Id);

    int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    int deleteAttrInfoById(Integer id);
}
