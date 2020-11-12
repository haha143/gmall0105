package com.auguigu.gmall.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PmsProductInfo implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String productName;

    @Column
    private String description;

    @Column
    private Integer catalog3Id;

    @Transient
    private List<PmsProductSaleAttr> pmsProductSaleAttrList;
    @Transient
    private List<PmsProductImage> pmsProductImageList;
}


