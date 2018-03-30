package org.ngo.user.model;

import java.util.Date;

public class UserProfile {

    private Integer id;
    private Boolean gender;
    private String avatar;
    private Date birthday;
    private String homeAddress;
    private Integer city;
    private String corpAddress;
    private Date createTime;
    private String comment;

    public UserProfile(Integer id) {
        this.id = id;
    }

    public UserProfile(Boolean gender, String avatar, Date birthday, String homeAddress, Integer city, String
            corpAddress, Date createTime, String comment) {
        this.gender = gender;
        this.avatar = avatar;
        this.birthday = birthday;
        this.homeAddress = homeAddress;
        this.city = city;
        this.corpAddress = corpAddress;
        this.createTime = createTime;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getCorpAddress() {
        return corpAddress;
    }

    public void setCorpAddress(String corpAddress) {
        this.corpAddress = corpAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
