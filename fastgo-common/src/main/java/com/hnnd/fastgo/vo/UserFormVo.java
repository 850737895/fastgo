package com.hnnd.fastgo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户注册vo
 * Created by Administrator on 2018/12/20.
 */
@Data
public class UserFormVo implements Serializable{

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;


    private String phone;

    @NotEmpty
    @Length(min = 10000,max = 999999)
    private String code;
}
