package com.hnnd.fastgo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 系统分页vo
 * Created by 85073 on 2018/11/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultVo<T> implements Serializable{

    private Long total;

    private List<T> result;
}
