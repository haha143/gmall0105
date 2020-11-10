package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsBaseAttrInfo;
import com.auguigu.gmall.bean.PmsBaseAttrValue;

import java.util.List;

public interface PmsBaseAttrValueService {
    List<PmsBaseAttrValue> getAttrValueList(Integer attrId);

    int  deleteAttrValueById(Integer id);

    int deleteAttrValueByAttrId(Integer id);
}
