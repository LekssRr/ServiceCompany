package ru.warrantyauto.sevice;

import java.util.List;

public interface IAutoService {
    String getServiceCompanyToVin(String vin);
    List<String> getAllAuto();
    boolean doesCarExist(String autoVin);
    boolean doesCarToServiceCompany(String autoVin, String nameServiceCompany);
    boolean deleteAuto(String vin);
    boolean addAuto(String newVin, String nameServiceCompany);
    boolean updateAuto(String vin, String newNameServiceCompany);
}
