package com.auguigu.gmall.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @param
 * @return
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PmsSkuInfo implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    Integer id;

    @Column
    Integer productId;

    @Transient
    Integer spuId;

    @Column
    BigDecimal price;

    @Column
    String skuName;

    @Column
    BigDecimal weight;

    @Column
    String skuDesc;

    @Column
    Integer catalog3Id;

    @Column
    String skuDefaultImg;

    @Column
    Integer tmId;

    @Transient
    List<PmsSkuImage> pmsSkuImageList;

    @Transient
    List<PmsSkuAttrValue> pmsSkuAttrValueList;

    @Transient
    List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList;
}
