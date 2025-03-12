package ru.warrantyauto.entities;

public class Auto {

    private String vinCode;
    private String nameServiceCompany;
    private ServiceCompany serviceCompan;

    public Auto(String newVinCode, String newNameServiceCompany, ServiceCompany newServiceCompan)
    {
        this.vinCode = newVinCode;
        this.nameServiceCompany = newNameServiceCompany;
        this.serviceCompan = newServiceCompan;
    }

    public String getName()
    {
        return vinCode;
    }
    String getVinCode()
    {
        return this.vinCode;
    }
    ServiceCompany getServiceCompan()
    {
        return serviceCompan;
    }
    String getNameServiceCompany()
    {
        return serviceCompan.getName();
    }
}
