package com.totsystems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "histories")
public class History implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "secid")
    private String secId;

    @Column(name = "tradedate")
    private Date tradeDate;

    @Column(name = "numtrades")
    private int numTrades;

    @Column(name = "open")
    private double open;

    @Column(name = "close")
    private double close;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "secid", referencedColumnName = "secid", insertable = false, updatable = false)
    private Security security;

    public History() {
    }

    public History(String secId, Date tradeDate, int numTrades, double open, double close) {
        this.secId = secId;
        this.tradeDate = tradeDate;
        this.numTrades = numTrades;
        this.open = open;
        this.close = close;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public int getNumTrades() {
        return numTrades;
    }

    public void setNumTrades(int numTrades) {
        this.numTrades = numTrades;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", secId='" + secId + '\'' +
                ", tradeDate=" + tradeDate +
                ", numTrades=" + numTrades +
                ", open=" + open +
                ", close=" + close +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return id == history.id && numTrades == history.numTrades && Double.compare(history.open, open) == 0 && Double.compare(history.close, close) == 0 && Objects.equals(secId, history.secId) && Objects.equals(tradeDate, history.tradeDate) && Objects.equals(security, history.security);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, secId, tradeDate, numTrades, open, close);
    }
}
