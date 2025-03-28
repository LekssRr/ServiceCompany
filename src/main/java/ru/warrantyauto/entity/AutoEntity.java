package ru.warrantyauto.entity;

public class AutoEntity {

    private String vinCode;
    private ServiceCompanyEntity serviceCompan;

    public AutoEntity(String newVinCode, String newNameServiceCompany, ServiceCompanyEntity newServiceCompan) {
        this.vinCode = newVinCode;
        this.serviceCompan = newServiceCompan;
    }

    public String getVin() {
        return this.vinCode;
    }

    public ServiceCompanyEntity getServiceCompan() {
        return serviceCompan;
    }

    public String getNameServiceCompany() {
        return serviceCompan.getName();
    }
}
