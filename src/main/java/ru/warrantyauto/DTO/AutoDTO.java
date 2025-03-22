package ru.warrantyauto.DTO;

import ru.warrantyauto.entity.ServiceCompanyEntity;

public class AutoDTO extends DTO
{
    private String vinCode;
    private String nameServiceCompany;
    private ServiceCompanyEntity serviceCompan;
    public AutoDTO(String newVinCode, String newNameServiceCompany)
    {
        this.vinCode = newVinCode;
        this.nameServiceCompany = newNameServiceCompany;
        this.serviceCompan = new ServiceCompanyEntity(nameServiceCompany);
    }
    public AutoDTO(String newVinCode)
    {
        this.vinCode = newVinCode;
    }


    public String getVin()
    {
        return this. vinCode;
    }

    public ServiceCompanyEntity getServiceCompan()
    {
        return serviceCompan;
    }
    public String getNameServiceCompany()
    {
        return serviceCompan.getName();
    }
}
