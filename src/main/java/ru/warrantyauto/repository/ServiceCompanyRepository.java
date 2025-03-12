package ru.warrantyauto.repository;

import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.model.Model;

import java.util.List;

public class ServiceCompanyRepository {

    private AutoRepository autoRepository = new AutoRepository();
    public String getNameServiceCompanyRepository(String nameServiceCompany)
    {
        for (int i =0; i<=Model.getInstance().getServiceCompany().size()-1; i++)
        {
            if(Model.getInstance().getServiceCompany().get(i).equals(nameServiceCompany))
            {
                return Model.getInstance().getServiceCompans().get(i).toString();
            }
        }
        return null;
    }

    public String getAllVinServiceCompanyRepository(String nameServiceCompany)
    {
        for (int i =0; i<=Model.getInstance().getServiceCompany().size()-1; i++)
        {
            if(Model.getInstance().getServiceCompany().get(i).equals(nameServiceCompany))
            {
                return Model.getInstance().getServiceCompans().get(i).allVinAuto().toString();
            }
        }
        return null;
    }

    public void addServiceCompanyRepository(ServiceCompany serviceCompany)
    {
        Model.getInstance().addServiceCompans(serviceCompany);
    }

    public boolean doesCarToServiceCompanyRepository(String autoVin)
    {
        return !Model.getInstance().addServiceCompanyString(autoVin);
    }

    public ServiceCompany getServiceCompanyToName(String nameServiceCompany)
    {
        autoRepository.deleteAutoFromServiceCompany(nameServiceCompany);

        ServiceCompany result = null;

        for(int i =0; i<=Model.getInstance().getServiceCompans().size()-1; i++)
        {
            if(Model.getInstance().getServiceCompans().get(i).getName().equals(nameServiceCompany))
            {
                result = Model.getInstance().getServiceCompans().get(i);
                return result;
            }
        }
        return result;
    }

    public List<String> getAllServiceCompanyToRepository() {

        return Model.getInstance().getServiceCompany();
    }

    public boolean addServiceCompanySet(String newServiceCompany)
    {
        return Model.getInstance().addServiceCompanyString(newServiceCompany);
    }

    public boolean deleteServiceCompanyRepository(String deleteCompany)
    {
        return Model.getInstance().deleteServiceCompany(deleteCompany);
    }
}
