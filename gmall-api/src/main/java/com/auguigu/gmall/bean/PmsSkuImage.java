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
public class PmsSkuImage implements Serializable {

    @Id
    @Column
    Integer id;
    @Column
    Integer skuId;
    @Column
    String imgName;
    @Column
    String imgUrl;
    @Column
    Integer productImgId;
    @Column
    String isDefault;
}