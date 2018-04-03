package org.ngo.corporation.model;

import java.io.Serializable;

public class CorporationStaff implements Serializable {

    private Integer corporation;
    private Integer person;
    private Integer department;

    public Integer getCorporation() {
        return corporation;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }
}
