package ru.warrantyauto.repository;

import ru.warrantyauto.entity.ServiceCompanyEntity;

import java.util.List;

public interface RepositoryServiceCompany {
    List<String> getAllAutoToServiceCompany(ServiceCompanyEntity nameServiceCompany);
    List<String> getAllServiceCompany();
}
