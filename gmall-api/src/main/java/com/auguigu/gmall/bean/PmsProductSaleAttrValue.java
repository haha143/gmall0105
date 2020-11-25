package com.auguigu.gmall.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PmsProductSaleAttrValue implements Serializable {
    @Id
    @Column
    Integer id;

    @Column
    Integer productId;

    @Column
    Integer saleAttrId;

    @Column
    String saleAttrValueName;

    @Transient
    Integer isChecked;

}
