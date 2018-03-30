package org.ngo.user.model;

import java.util.Date;

public class User {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String realname;
    private String usercode;
    private String email;
    private String mobile;
    private String wechat;
    private String weibo;
    private Date last_login;
    private String last_log_ip;
    private UserProfile userProfile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Date getLast_log_date() {
        return last_login;
    }

    public void setLast_log_date(Date last_log_date) {
        this.last_login = last_log_date;
    }

    public String getLast_log_ip() {
        return last_log_ip;
    }

    public void setLast_log_ip(String last_log_ip) {
        this.last_log_ip = last_log_ip;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", usercode='" + usercode + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", wechat='" + wechat + '\'' +
                ", weibo='" + weibo + '\'' +
                ", last_login=" + last_login +
                ", last_log_ip='" + last_log_ip + '\'' +
                ", userProfile=" + userProfile +
                '}';
    }
}