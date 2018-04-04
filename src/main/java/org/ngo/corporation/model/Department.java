package org.ngo.corporation.model;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {

    private static final long serialVersionUID = -2205126624499836318L;
    private Integer id;
    private String name;
    private Integer type;
    private Integer corporation;
    private Integer parent;
    private Department parentDepart;
    private List<Department> childrenDepart;

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

    public Integer getCorporation() {
        return corporation;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Department getParentDepart() {
        return parentDepart;
    }

    public void setParentDepart(Department parentDepart) {
        this.parentDepart = parentDepart;
        this.parent = parentDepart.getId();
    }

    public List<Department> getChildrenDepart() {
        return childrenDepart;
    }

    public void setChildrenDepart(List<Department> childrenDepart) {
        this.childrenDepart = childrenDepart;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", corporation=" + corporation +
                ", parent=" + parent +
                '}';
    }
}
