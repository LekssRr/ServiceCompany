package ru.warrantyauto.repository;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.model.Model;

public class AutoRepository {
    ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
    public void addAutoToRepository(String newVin, String nameServiceCompany)
    {
        ServiceCompany currentServiceCompany = serviceCompanyRepository.getServiceCompanyToName(nameServiceCompany);
        Auto newAuto = new Auto(newVin, nameServiceCompany, currentServiceCompany);
        Model.getInstance().add(newAuto, nameServiceCompany);
    }
    public boolean doesCarExistToRepository(String autoVin)
    {
        return Model.getInstance().addVin(autoVin);
    }
    public boolean doesCarToServiceCompanyRepository(String autoVin)
    {
        return serviceCompanyRepository.doesCarToServiceCompanyRepository(autoVin);
    }
    public void deleteAutoFromServiceCompany(String nameServiceCompany)
    {
        for(int i = 0; i<= Model.getInstance().getAuto().size()-1; i++)
        {
            if (Model.getInstance().getAuto().get(i).getName().equals(nameServiceCompany))
            {
                Model.getInstance().getAuto().remove(i);
            }
        }
    }
}
