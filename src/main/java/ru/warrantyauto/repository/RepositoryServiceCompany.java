package ru.warrantyauto.repository;

import ru.warrantyauto.DTO.ServiceCompanyDTO;

import java.util.List;

public interface RepositoryServiceCompany {
    List<String> getAllAutoToServiceCompany(ServiceCompanyDTO nameServiceCompany);
    List<String> getAllServiceCompany();
}
