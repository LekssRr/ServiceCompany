package ru.warrantyauto.repository;

import ru.warrantyauto.entities.ServiceCompany;

import java.util.List;

public interface RepositoryServiceCompany {
    List<String> getAllAutoToServiceCompany(ServiceCompany nameServiceCompany);
    List<String> getAllServiceCompany();
}
