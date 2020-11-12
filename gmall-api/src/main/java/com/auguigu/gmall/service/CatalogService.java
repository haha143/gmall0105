package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.PmsBaseCatalog1;
import com.auguigu.gmall.bean.PmsBaseCatalog2;
import com.auguigu.gmall.bean.PmsBaseCatalog3;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(Integer catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(Integer catalog2Id);
}
