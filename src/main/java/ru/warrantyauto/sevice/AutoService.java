package ru.warrantyauto.sevice;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.List;

public class AutoService implements DeleteAuto, AddAuto, GetInfoAuto, UpdateAuto {
    AutoRepository autoRepository = new AutoRepository();

    @Override
    public boolean deleteAuto(String vin)
    {
        System.out.println(autoRepository.get(vin));
        Auto deleteAuto = new Auto(vin, autoRepository.get(vin), new ServiceCompany(autoRepository.get(vin)));
        return autoRepository.delete(deleteAuto);
    }
    @Override
    public boolean addAuto(String newVin, String nameServiceCompany)
    {
        autoRepository.addAutoToRepository(newVin, nameServiceCompany);
        //ServiceCompany currentServiceCompany = autoRepository.getServiceCompanyRepository().getServiceCompanyToName(nameServiceCompany);
        return autoRepository.create(new Auto(newVin, nameServiceCompany, new ServiceCompany(nameServiceCompany)));
    }
    @Override
    public Auto getAuto(String vin)
    {
        return new Auto(vin,autoRepository.get(vin), new ServiceCompany(autoRepository.get(vin)));
    }
    @Override
    public List<Auto> getAllAuto(){
        List<Auto> result = new ArrayList<>();
        for(int i = 0; i<= autoRepository.getAllAutoVin().size()-1; i++)
        {
            result.add(new Auto(autoRepository.getAllAutoVin().get(i), autoRepository.getAllAutoServiceCompany().get(i), new ServiceCompany(autoRepository.getAllAutoServiceCompany().get(i))));
        }
        return result;
    };
    @Override
    public boolean doesCarExist(String autoVin)
    {
        return autoRepository.doesCarExistToRepository(autoVin);
    }
    @Override
    public boolean doesCarToServiceCompany(String autoVin)
    {
        return autoRepository.doesCarToServiceCompanyRepository(autoVin);
    }

    @Override
    public boolean updateAuto(String vin, String newNameServiceCompany) {
        Auto updateAuto = new Auto(vin, newNameServiceCompany, new ServiceCompany(newNameServiceCompany));
        return autoRepository.update(updateAuto);
    }
}

interface GetInfoAuto{
    Auto getAuto(String vin);
    List<Auto> getAllAuto();
    boolean doesCarExist(String autoVin);
    boolean doesCarToServiceCompany(String autoVin);
}
interface DeleteAuto {
    boolean deleteAuto(String vin);
}
interface AddAuto{
    boolean addAuto(String newVin, String nameServiceCompany);
}
interface UpdateAuto {
    boolean updateAuto(String vin, String newNameServiceCompany);
}