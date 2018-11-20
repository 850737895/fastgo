package com.hnnd.fastgo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 规格VO
 * Created by Administrator on 2018/11/16.
 */
@Data
public class SpecVo implements Serializable {

    private Long id;

    private String specName;

    private List<SpecOpsVo> specOps;
}
