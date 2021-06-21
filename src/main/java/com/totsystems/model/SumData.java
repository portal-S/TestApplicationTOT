package com.totsystems.model;

import javax.persistence.Column;
import java.util.Date;

public class SumData {


    private String secId;

    private String regNumber;

    private String name;

    private String emitentTitle;

    private Date tradeDate;

    private int numTrades;

    private double open;

    private double close;

    public SumData(String secId, String regNumber, String name, String emitentTitle, Date tradeDate, int numTrades, double open, double close) {
        this.secId = secId;
        this.regNumber = regNumber;
        this.name = name;
        this.emitentTitle = emitentTitle;
        this.tradeDate = tradeDate;
        this.numTrades = numTrades;
        this.open = open;
        this.close = close;
    }

    public SumData() {
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
}
