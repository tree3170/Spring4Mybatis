package com.mybatis.model;

public class UserRole {
    private Integer id;

    private Integer tuserid;

    private Integer troleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTuserid() {
        return tuserid;
    }

    public void setTuserid(Integer tuserid) {
        this.tuserid = tuserid;
    }

    public Integer getTroleid() {
        return troleid;
    }

    public void setTroleid(Integer troleid) {
        this.troleid = troleid;
    }
}