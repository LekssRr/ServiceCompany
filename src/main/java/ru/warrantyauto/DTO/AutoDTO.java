package ru.warrantyauto.DTO;

public class AutoDTO extends DTO {

    private String vinCode;
    private String nameServiceCompany;
    private ServiceCompanyDTO serviceCompan;
    public AutoDTO(String newVinCode, String newNameServiceCompany, ServiceCompanyDTO newServiceCompan)
    {
        this.vinCode = newVinCode;
        this.nameServiceCompany = newNameServiceCompany;
        this.serviceCompan = newServiceCompan;
    }

    public String getVin()
    {
        return this. vinCode;
    }

    public ServiceCompanyDTO getServiceCompan()
    {
        return serviceCompan;
    }
    public String getNameServiceCompany()
    {
        return serviceCompan.getName();
    }
}
