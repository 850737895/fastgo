package com.hnnd.fastgo.entity;

import java.io.Serializable;

public class TbAreas implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_areas.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_areas.areaid
     *
     * @mbggenerated
     */
    private String areaid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_areas.area
     *
     * @mbggenerated
     */
    private String area;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_areas.cityid
     *
     * @mbggenerated
     */
    private String cityid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_areas
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_areas.id
     *
     * @return the value of tb_areas.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_areas.id
     *
     * @param id the value for tb_areas.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_areas.areaid
     *
     * @return the value of tb_areas.areaid
     *
     * @mbggenerated
     */
    public String getAreaid() {
        return areaid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_areas.areaid
     *
     * @param areaid the value for tb_areas.areaid
     *
     * @mbggenerated
     */
    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_areas.area
     *
     * @return the value of tb_areas.area
     *
     * @mbggenerated
     */
    public String getArea() {
        return area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_areas.area
     *
     * @param area the value for tb_areas.area
     *
     * @mbggenerated
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_areas.cityid
     *
     * @return the value of tb_areas.cityid
     *
     * @mbggenerated
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_areas.cityid
     *
     * @param cityid the value for tb_areas.cityid
     *
     * @mbggenerated
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_areas
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", areaid=").append(areaid);
        sb.append(", area=").append(area);
        sb.append(", cityid=").append(cityid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}