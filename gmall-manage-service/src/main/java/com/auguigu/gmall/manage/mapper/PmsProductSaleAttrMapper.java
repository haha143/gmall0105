package com.auguigu.gmall.manage.mapper;

import com.auguigu.gmall.bean.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> selectspuSaleAttrListCheckBySku(@Param("productId") Integer productId,@Param("skuId")Integer skuId);
}
