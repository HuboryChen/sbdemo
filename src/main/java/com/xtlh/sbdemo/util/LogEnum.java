package com.xtlh.sbdemo.util;

public enum LogEnum {

    BUSSINESS("bussiness"),
    PLATFORM("platform"),
    DB("db"),
    Exception("exception");

    private String category;

    LogEnum(String category) {this.category = category;}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
