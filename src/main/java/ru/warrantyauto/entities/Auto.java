package ru.warrantyauto.entities;

public class Auto extends Entity{

    private String vinCode;
    private String nameServiceCompany;
    private ServiceCompany serviceCompan;
    public Auto(String newVinCode, String newNameServiceCompany, ServiceCompany newServiceCompan)
    {
        this.vinCode = newVinCode;
        this.nameServiceCompany = newNameServiceCompany;
        this.serviceCompan = newServiceCompan;
    }

    public String getVin()
    {
        return this. vinCode;
    }

    public ServiceCompany getServiceCompan()
    {
        return serviceCompan;
    }
    public String getNameServiceCompany()
    {
        return serviceCompan.getName();
    }
}
