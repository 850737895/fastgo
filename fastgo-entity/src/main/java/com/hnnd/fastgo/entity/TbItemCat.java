package com.hnnd.fastgo.entity;

import java.io.Serializable;

public class TbItemCat implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_cat.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_cat.parent_id
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_cat.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_cat.type_id
     *
     * @mbggenerated
     */
    private Long typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_item_cat
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_cat.id
     *
     * @return the value of tb_item_cat.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_cat.id
     *
     * @param id the value for tb_item_cat.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_cat.parent_id
     *
     * @return the value of tb_item_cat.parent_id
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_cat.parent_id
     *
     * @param parentId the value for tb_item_cat.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_cat.name
     *
     * @return the value of tb_item_cat.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_cat.name
     *
     * @param name the value for tb_item_cat.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_cat.type_id
     *
     * @return the value of tb_item_cat.type_id
     *
     * @mbggenerated
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_cat.type_id
     *
     * @param typeId the value for tb_item_cat.type_id
     *
     * @mbggenerated
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
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
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", typeId=").append(typeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}