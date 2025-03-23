package ru.warrantyauto.entity;

public class AutoEntity extends Entity {

    private String vinCode;
    private String nameServiceCompany;
    private ServiceCompanyEntity serviceCompan;

    public AutoEntity(String newVinCode, String newNameServiceCompany, ServiceCompanyEntity newServiceCompan) {
        this.vinCode = newVinCode;
        this.nameServiceCompany = newNameServiceCompany;
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
