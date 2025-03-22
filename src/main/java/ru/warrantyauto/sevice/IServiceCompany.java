package ru.warrantyauto.sevice;

import ru.warrantyauto.DTO.ServiceCompanyDTO;

import java.util.List;

public interface IServiceCompany {
    default String getAllVinServiceCompany(String nameServiceCompany)
    {
        return null;
    }
    default List<ServiceCompanyDTO> getAllServiceCompany() {return null;}
    boolean addServiceCompany(String nameServiceCompany);
    boolean updateServiceCompany(String oldServiceCompanyName, String newServiceCompanyName);
    boolean deleteServiceCompany(String nameServiceCompany);
}
