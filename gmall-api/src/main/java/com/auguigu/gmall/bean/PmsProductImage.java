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
public class PmsProductImage implements Serializable {

    @Column
    @Id
    private Integer id;
    @Column
    private Integer productId;
    @Column
    private String imgName;
    @Column
    private String imgUrl;
}