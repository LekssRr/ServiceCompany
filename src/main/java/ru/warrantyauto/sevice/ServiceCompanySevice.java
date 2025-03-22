package ru.warrantyauto.sevice;

import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceCompanySevice implements AddServiceCompany, DeleteServiceCompany, GetServiceCompany {

    DBConnectionProvider dbConnectionProvider;
    final ServiceCompanyRepository serviceCompanyRepository;
    final AutoRepository autoRepository;
    public ServiceCompanySevice(DBConnectionProvider newDbConnectionProvider)
    {
        dbConnectionProvider = newDbConnectionProvider;
        this.serviceCompanyRepository = new ServiceCompanyRepository(newDbConnectionProvider);
        this.autoRepository = new AutoRepository(newDbConnectionProvider);
    }
    @Override
    public boolean addServiceCompany(String nameServiceCompany)
    {
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if(setServiceCompany.add(nameServiceCompany))
        {
            serviceCompanyRepository.create(new ServiceCompanyDTO(nameServiceCompany));
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
            ArrayList<String> vinListDeleteAuto = new ArrayList<>(serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompanyDTO(nameServiceCompany)));
            for(int i = 0; i <= vinListDeleteAuto.size()-1; i++)
            {
                autoRepository.delete(new AutoDTO(vinListDeleteAuto.get(i), nameServiceCompany, new ServiceCompanyDTO(nameServiceCompany)));
            }
            serviceCompanyRepository.delete(new ServiceCompanyDTO(nameServiceCompany));
            return true;
        }
        return false;
    }
    @Override
    public String getAllVinServiceCompany(String nameServiceCompany)
    {
        return serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompanyDTO(nameServiceCompany)).toString();
    }

    @Override
    public List<String> getAllServiceCompany() {

        return serviceCompanyRepository.getAllServiceCompany();
    }

    @Override
    public boolean updateServiceCompany(String oldServiceCompanyName, String newServiceCompanyName) {

        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if(!setServiceCompany.add(oldServiceCompanyName))
        {
            ArrayList<String> vinList = new ArrayList<>(serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompanyDTO(oldServiceCompanyName)));

            System.out.println(vinList);
            serviceCompanyRepository.updateServiceCompany(new ServiceCompanyDTO(oldServiceCompanyName), new ServiceCompanyDTO(newServiceCompanyName));
            for(int i = 0; i<vinList.size(); i++)
            {
                autoRepository.delete(new AutoDTO(vinList.get(i), oldServiceCompanyName, new ServiceCompanyDTO(oldServiceCompanyName)));
                autoRepository.create(new AutoDTO(vinList.get(i), newServiceCompanyName, new ServiceCompanyDTO(newServiceCompanyName)));
            }
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
    boolean updateServiceCompany(String oldServiceCompanyName, String newServiceCompanyName);
}
interface DeleteServiceCompany
{
    boolean deleteServiceCompany(String nameServiceCompany);
}