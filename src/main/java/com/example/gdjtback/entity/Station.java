package com.example.gdjtback.entity;

public class Station {
    private Integer id;

    private String stationid;

    private String stationname;

    private String lineid;

    private Long dlongitude;

    private Long dlatitude;

    private Integer deleteflag;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid == null ? null : stationid.trim();
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname == null ? null : stationname.trim();
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid == null ? null : lineid.trim();
    }

    public Long getDlongitude() {
        return dlongitude;
    }

    public void setDlongitude(Long dlongitude) {
        this.dlongitude = dlongitude;
    }

    public Long getDlatitude() {
        return dlatitude;
    }

    public void setDlatitude(Long dlatitude) {
        this.dlatitude = dlatitude;
    }

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}