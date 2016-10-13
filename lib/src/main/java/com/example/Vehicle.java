package com.example;

import java.util.List;

/**
 * Created by Jess Yuan on 11/10/2016.
 */

public class Vehicle {

    /**
     * registrationno : 软件开发部测试2(&2)
     * commno : 15820557343
     * vehicleid : 21685
     * terminaltypename : 202
     * terminaltypeid : 1
     * extime : 1899-12-30 00:00:00.0
     * userinfolist : []
     * timestamp : 783296523
     * operation : insert
     * registrationnocolor : registrationnocolor
     */

    private String registrationno;
    private String commno;
    private int vehicleid;
    private String terminaltypename;
    private int terminaltypeid;
    private String extime;
    private String timestamp;
    private String operation;
    private String registrationnocolor;
    private List<?> userinfolist;

    public String getRegistrationno() {
        return registrationno;
    }

    public void setRegistrationno(String registrationno) {
        this.registrationno = registrationno;
    }

    public String getCommno() {
        return commno;
    }

    public void setCommno(String commno) {
        this.commno = commno;
    }

    public int getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getTerminaltypename() {
        return terminaltypename;
    }

    public void setTerminaltypename(String terminaltypename) {
        this.terminaltypename = terminaltypename;
    }

    public int getTerminaltypeid() {
        return terminaltypeid;
    }

    public void setTerminaltypeid(int terminaltypeid) {
        this.terminaltypeid = terminaltypeid;
    }

    public String getExtime() {
        return extime;
    }

    public void setExtime(String extime) {
        this.extime = extime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRegistrationnocolor() {
        return registrationnocolor;
    }

    public void setRegistrationnocolor(String registrationnocolor) {
        this.registrationnocolor = registrationnocolor;
    }

    public List<?> getUserinfolist() {
        return userinfolist;
    }

    public void setUserinfolist(List<?> userinfolist) {
        this.userinfolist = userinfolist;
    }
}
