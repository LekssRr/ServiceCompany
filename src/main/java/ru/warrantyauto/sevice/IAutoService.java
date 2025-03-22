package ru.warrantyauto.sevice;

import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;

import java.util.List;

public interface IAutoService {
    ServiceCompanyDTO getServiceCompanyToVin(String vin);
    List<AutoDTO> getAllAuto();
    boolean doesCarExist(String autoVin);
    boolean doesCarToServiceCompany(String autoVin, String nameServiceCompany);
    boolean deleteAuto(String vin);
    boolean addAuto(String newVin, String nameServiceCompany);
    boolean updateAuto(String vin, String newNameServiceCompany);
}
