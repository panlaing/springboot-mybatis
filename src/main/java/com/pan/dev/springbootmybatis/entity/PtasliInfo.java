package com.pan.dev.springbootmybatis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PtasliInfo {
    private Long id;

    private Long passportId;

    private Boolean name;

    private Boolean birthDate;

    private Boolean birthPlace;

    private String address;

    private BigDecimal selfiePhoto;

    private String product;

    private Integer version;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public Boolean getName() {
        return name;
    }

    public void setName(Boolean name) {
        this.name = name;
    }

    public Boolean getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Boolean birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Boolean birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getSelfiePhoto() {
        return selfiePhoto;
    }

    public void setSelfiePhoto(BigDecimal selfiePhoto) {
        this.selfiePhoto = selfiePhoto;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}