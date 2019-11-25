package com.wisewin.backend.entity.bo;

import java.util.Date;

public class StatisticalBO {
    private Long value;
    private String name;
    private String date;
    private String year;
    private String month;
    private String day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public StatisticalBO(){}
    public StatisticalBO(Long value,String name){
        this.value=value;
        this.name=name;
    }
    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
