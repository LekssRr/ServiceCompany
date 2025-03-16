package ru.warrantyauto.sevice;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceCompanySevice implements AddServiceCompany, DeleteServiceCompany, GetServiceCompany {
    final ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
    final AutoRepository autoRepository = new AutoRepository();
    @Override
    public boolean addServiceCompany(String nameServiceCompany)
    {
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if(setServiceCompany.add(nameServiceCompany))
        {
            serviceCompanyRepository.create(new ServiceCompany(nameServiceCompany));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteServiceCompany(String nameServiceCompany)
    {
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if(!setServiceCompany.add(nameServiceCompany))
        {
            ArrayList<String> vinListDeleteAuto = new ArrayList<>(serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompany(nameServiceCompany)));
            for(int i = 0; i <= vinListDeleteAuto.size()-1; i++)
            {
                autoRepository.delete(new Auto(vinListDeleteAuto.get(i), nameServiceCompany, new ServiceCompany(nameServiceCompany)));
            }
            serviceCompanyRepository.delete(new ServiceCompany(nameServiceCompany));
            return true;
        }
        return false;
    }
    @Override
    public String getAllVinServiceCompany(String nameServiceCompany)
    {
        return serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompany(nameServiceCompany)).toString();
    }

    @Override
    public List<String> getAllServiceCompany() {

        return serviceCompanyRepository.getAllServiceCompany();
    }

    @Override
    public boolean updateServiceCompany(String nameServiceCompany) {
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if(!setServiceCompany.add(nameServiceCompany))
        {
            serviceCompanyRepository.update(new ServiceCompany(nameServiceCompany));
            return true;
        }
        return false;
    }
}

interface GetServiceCompany{
    default String getAllVinServiceCompany(String nameServiceCompany)
    {
        return null;
    }
    default List<String> getAllServiceCompany() {return null;}
}
interface AddServiceCompany{

    boolean addServiceCompany(String nameServiceCompany);
    boolean updateServiceCompany(String nameServiceCompany);
}
interface DeleteServiceCompany
{
    boolean deleteServiceCompany(String nameServiceCompany);
}