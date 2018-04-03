package org.ngo.corporation.model;

import java.io.Serializable;

public class Corporation implements Serializable {

    private Integer id;                 //企业ID
    private String name;                //企业名称
    private Integer domain;             //企业领域
    private Integer type;               //企业类型
    private String address;             //地址
    private String accountablePerson;   //负责人/法人代表
    private String code;                //企业编号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDomain() {
        return domain;
    }

    public void setDomain(Integer domain) {
        this.domain = domain;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountablePerson() {
        return accountablePerson;
    }

    public void setAccountablePerson(String accountablePerson) {
        this.accountablePerson = accountablePerson;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "Corporation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domain=" + domain +
                ", type=" + type +
                ", address='" + address + '\'' +
                ", accountablePerson='" + accountablePerson + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
