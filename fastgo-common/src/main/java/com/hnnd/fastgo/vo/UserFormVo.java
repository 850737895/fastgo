package com.hnnd.fastgo.vo;

import com.hnnd.fastgo.anno.IsMobile;
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

    @IsMobile
    private String phone;

    @NotEmpty
    @Length(min = 6,max = 6)
    private String code;
}
