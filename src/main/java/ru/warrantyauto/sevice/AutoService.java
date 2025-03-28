package ru.warrantyauto.sevice;

import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.config.DBConnectionProvider;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoService implements IAutoService {

    final AutoRepository autoRepository;
    final ServiceCompanyRepository serviceCompanyRepository;

    public AutoService(DBConnectionProvider newDbConnectionProvider) {

        autoRepository = new AutoRepository(newDbConnectionProvider);
        serviceCompanyRepository = new ServiceCompanyRepository(newDbConnectionProvider);
    }

    @Override
    public boolean deleteAuto(String vin) {
        AutoEntity deleteAuto = new AutoEntity(vin, autoRepository.get(vin), new ServiceCompanyEntity(autoRepository.get(vin)));
        serviceCompanyRepository.deleteVinToServiceCompany(deleteAuto);
        return autoRepository.delete(deleteAuto);
    }

    @Override
    public boolean addAuto(String newVin, String nameServiceCompany) {
        boolean res = false;
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if (!setServiceCompany.add(nameServiceCompany)) {
            AutoEntity newAuto = new AutoEntity(newVin, nameServiceCompany, new ServiceCompanyEntity(nameServiceCompany));
            autoRepository.create(newAuto);
            res = serviceCompanyRepository.addVinToServiceCompany(newAuto);
        }
        return res;
    }

    @Override
    public ServiceCompanyDTO getServiceCompanyToVin(String vin) {
        ServiceCompanyDTO result = new ServiceCompanyDTO(autoRepository.get(vin));
        return result;
    }

    @Override
    public List<AutoDTO> getAllAuto() {
        List<AutoDTO> result = new ArrayList<>();
        List<String> resultString = new ArrayList<>(autoRepository.getAllAutoVin());
        for (int i = 0; i <= resultString.size() - 1; i++) {
            result.add(new AutoDTO(resultString.get(i).toString()));
        }
        return result;
    }

    ;

    @Override
    public boolean doesCarExist(String autoVin) {
        boolean resulat = false;
        for (int i = 0; i <= autoRepository.getAllAutoVin().size() - 1; i++) {
            if (autoRepository.getAllAutoVin().get(i).equals(autoVin)) {
                resulat = true;
            }
        }
        return resulat;
    }

    @Override
    public boolean doesCarToServiceCompany(String autoVin, String nameServiceCompany) {
        AutoEntity newAuto = new AutoEntity(autoVin, nameServiceCompany, new ServiceCompanyEntity(nameServiceCompany));
        return autoRepository.doesCarToServiceCompanyRepository(newAuto);
    }

    @Override
    public boolean updateAuto(String vin, String newNameServiceCompany) {
        String deleteVinSC = this.getServiceCompanyToVin(vin).getName();
        AutoEntity deleteAuto = new AutoEntity(vin, deleteVinSC, new ServiceCompanyEntity(deleteVinSC));
        AutoEntity updateAuto = new AutoEntity(vin, newNameServiceCompany, new ServiceCompanyEntity(newNameServiceCompany));
        serviceCompanyRepository.addVinToServiceCompany(updateAuto);
        serviceCompanyRepository.deleteVinToServiceCompany(deleteAuto);
        return autoRepository.update(updateAuto);
    }
}
