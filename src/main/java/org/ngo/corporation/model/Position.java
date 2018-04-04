package org.ngo.corporation.model;

import java.io.Serializable;

public class Position implements Serializable {

    private static final long serialVersionUID = 435700593241913822L;
    private Integer id;
    private String name;
    private Integer type;
    private String comment;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", comment='" + comment + '\'' +
                '}';
    }
}
