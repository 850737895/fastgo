package com.hnnd.fastgo.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品品牌bo
 * Created by Administrator on 2018/11/14.
 */
@Data
public class BrandBo implements Serializable {

    private Long id;

    private String name;

    private String firstChar;
}
