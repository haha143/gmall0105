package com.auguigu.gmall.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @param
 * @return
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PmsSkuSaleAttrValue implements Serializable {

    @Id
    @Column
    Integer id;

    @Column
    Integer skuId;

    @Column
    Integer saleAttrId;

    @Column
    Integer saleAttrValueId;

    @Column
    String saleAttrName;

    @Column
    String saleAttrValueName;
}
