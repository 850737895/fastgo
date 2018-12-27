package com.hnnd.fastgo.vo;

import com.hnnd.fastgo.anno.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * Created by Administrator on 2018/12/27.
 */
@Data
public class AddressVo {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_address.province_id
     *
     * @mbggenerated
     */
    @NotEmpty
    private String provinceId;

    private long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_address.city_id
     *
     * @mbggenerated
     */
    @NotEmpty
    private String cityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_address.town_id
     *
     * @mbggenerated
     */
    @NotEmpty
    private String townId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_address.mobile
     *
     * @mbggenerated
     */
    @IsMobile
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_address.address
     *
     * @mbggenerated
     */
    @NotEmpty
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_address.contact
     *
     * @mbggenerated
     */
    @NotEmpty
    private String contact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_address.is_default
     *
     * @mbggenerated
     */
    private String isDefault;



    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_address.alias
     *
     * @mbggenerated
     */
    private String alias;

}
