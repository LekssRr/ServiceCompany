package ru.warrantyauto.sevice;

import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.List;

public class ServiceCompanySevice implements AddServiceCompany, DeleteServiceCompany, GetServiceCompany {
    ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
    @Override
    public void addServiceCompany(String nameServiceCompany)
    {
        serviceCompanyRepository.addServiceCompanyRepository(new ServiceCompany(nameServiceCompany));
    }
    @Override
    public boolean deleteServiceCompany(String nameServiceCompany)
    {
        return serviceCompanyRepository.deleteServiceCompanyRepository(nameServiceCompany);
    }
    @Override
    public String getAllVinServiceCompany(String nameServiceCompany)
    {
        return serviceCompanyRepository.getAllVinServiceCompanyRepository(nameServiceCompany);
    }
    @Override
    public String getNameServiceCompany(String nameServiceCompany)
    {
        return serviceCompanyRepository.getNameServiceCompanyRepository(nameServiceCompany);
    }
    @Override
    public List<String> getAllServiceCompany() {

        return serviceCompanyRepository.getAllServiceCompanyToRepository();
    }
    @Override
    public boolean addServiceCompanyName(String nameServiceCompany)
    {
        return serviceCompanyRepository.addServiceCompanySet(nameServiceCompany);
    }
}

interface GetServiceCompany{
    default String getAllVinServiceCompany(String nameServiceCompany)
    {
        return null;
    }
    default String getNameServiceCompany(String nameServiceCompany)
    {
        return null;
    }
    default List<String> getAllServiceCompany() {return null;}
}
interface AddServiceCompany{

    default void addServiceCompany(String nameServiceCompany)
    {
    }
    default boolean addServiceCompanyName(String nameServiceCompany)
    {
        return false;
    }
}
interface DeleteServiceCompany
{
    default boolean deleteServiceCompany(String nameServiceCompany)
    {
        return false;
    }
}