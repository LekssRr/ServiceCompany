package ru.warrantyauto.sevice;

import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceCompanySevice implements IServiceCompany {

    DBConnectionProvider dbConnectionProvider;
    final ServiceCompanyRepository serviceCompanyRepository;
    final AutoRepository autoRepository;

    public ServiceCompanySevice(DBConnectionProvider newDbConnectionProvider) {
        dbConnectionProvider = newDbConnectionProvider;
        this.serviceCompanyRepository = new ServiceCompanyRepository(newDbConnectionProvider);
        this.autoRepository = new AutoRepository(newDbConnectionProvider);
    }

    @Override
    public boolean addServiceCompany(String nameServiceCompany) {
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if (setServiceCompany.add(nameServiceCompany)) {
            serviceCompanyRepository.create(new ServiceCompanyEntity(nameServiceCompany));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteServiceCompany(String nameServiceCompany) {
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if (!setServiceCompany.add(nameServiceCompany)) {
            ArrayList<String> vinListDeleteAuto = new ArrayList<>(serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompanyEntity(nameServiceCompany)));
            for (int i = 0; i <= vinListDeleteAuto.size() - 1; i++) {
                autoRepository.delete(new AutoEntity(vinListDeleteAuto.get(i), nameServiceCompany, new ServiceCompanyEntity(nameServiceCompany)));
            }
            serviceCompanyRepository.delete(new ServiceCompanyEntity(nameServiceCompany));
            return true;
        }
        return false;
    }

    @Override
    public String getAllVinServiceCompany(String nameServiceCompany) {
        return serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompanyEntity(nameServiceCompany)).toString();
    }

    @Override
    public List<ServiceCompanyDTO> getAllServiceCompany() {

        ArrayList<ServiceCompanyDTO> result = new ArrayList<>();
        for (int i = 0; i <= serviceCompanyRepository.getAllServiceCompany().size() - 1; i++) {
            result.add(new ServiceCompanyDTO(serviceCompanyRepository.getAllServiceCompany().get(i)));
        }
        return result;
    }

    @Override
    public boolean updateServiceCompany(String oldServiceCompanyName, String newServiceCompanyName) {

        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if (!setServiceCompany.add(oldServiceCompanyName)) {
            ArrayList<String> vinList = new ArrayList<>(serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompanyEntity(oldServiceCompanyName)));

            System.out.println(vinList);
            serviceCompanyRepository.updateServiceCompany(new ServiceCompanyEntity(oldServiceCompanyName), new ServiceCompanyEntity(newServiceCompanyName));
            for (int i = 0; i < vinList.size(); i++) {
                autoRepository.delete(new AutoEntity(vinList.get(i), oldServiceCompanyName, new ServiceCompanyEntity(oldServiceCompanyName)));
                autoRepository.create(new AutoEntity(vinList.get(i), newServiceCompanyName, new ServiceCompanyEntity(newServiceCompanyName)));
            }
            return true;
        }
        return false;
    }
}
