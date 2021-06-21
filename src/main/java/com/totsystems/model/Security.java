package com.totsystems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "securities")
public class Security implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "secid")
    private String secId;

    @Column(name = "regnumber")
    private String regNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "emitent_title")
    private String emitentTitle;

    @OneToMany(mappedBy = "security")
    private List<History> historyList;

    public Security() {
    }

    public Security(int id, String secId, String regNumber, String name, String emitentTitle) {
        this.id = id;
        this.secId = secId;
        this.regNumber = regNumber;
        this.name = name;
        this.emitentTitle = emitentTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmitentTitle() {
        return emitentTitle;
    }

    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", secId='" + secId + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", name='" + name + '\'' +
                ", emitentTitle='" + emitentTitle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Security security = (Security) o;
        return id == security.id && Objects.equals(secId, security.secId) && Objects.equals(regNumber, security.regNumber) && Objects.equals(name, security.name) && Objects.equals(emitentTitle, security.emitentTitle) && Objects.equals(historyList, security.historyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, secId, regNumber, name, emitentTitle);
    }
}
