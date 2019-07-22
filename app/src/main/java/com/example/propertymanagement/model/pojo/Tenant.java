package com.example.propertymanagement.model.pojo;

public class Tenant {
    private String id;
    private String tenantname;
    private String tenantemail;
    private String tenantaddress;
    private String tenantmobile;
    private String propertyid;
    private String landlordid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantname() {
        return tenantname;
    }

    public void setTenantname(String tenantname) {
        this.tenantname = tenantname;
    }

    public String getTenantemail() {
        return tenantemail;
    }

    public void setTenantemail(String tenantemail) {
        this.tenantemail = tenantemail;
    }

    public String getTenantaddress() {
        return tenantaddress;
    }

    public void setTenantaddress(String tenantaddress) {
        this.tenantaddress = tenantaddress;
    }

    public String getTenantmobile() {
        return tenantmobile;
    }

    public void setTenantmobile(String tenantmobile) {
        this.tenantmobile = tenantmobile;
    }

    public String getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid;
    }

    public String getLandlordid() {
        return landlordid;
    }

    public void setLandlordid(String landlordid) {
        this.landlordid = landlordid;
    }
}
