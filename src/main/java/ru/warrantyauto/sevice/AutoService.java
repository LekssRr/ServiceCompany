package ru.warrantyauto.sevice;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoService implements DeleteAuto, AddAuto, GetInfoAuto, UpdateAuto {
    final AutoRepository autoRepository = new AutoRepository();
    final ServiceCompanyRepository  serviceCompanyRepository = new ServiceCompanyRepository();
    @Override
    public boolean deleteAuto(String vin)
    {
        Auto deleteAuto = new Auto(vin, autoRepository.get(vin), new ServiceCompany(autoRepository.get(vin)));
        serviceCompanyRepository.deleteVinToServiceCompany(deleteAuto);
        return autoRepository.delete(deleteAuto);
    }
    @Override
    public boolean addAuto(String newVin, String nameServiceCompany)
    {
        boolean res = false;
        ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if(!setServiceCompany.add(nameServiceCompany))
        {
            Auto newAuto = new Auto(newVin, nameServiceCompany, new ServiceCompany(nameServiceCompany));
            autoRepository.create(newAuto);
            res = serviceCompanyRepository.addVinToServiceCompany(newAuto);
        }
        return res;
    }
    @Override
    public Auto getAuto(String vin)
    {
        return new Auto(vin,autoRepository.get(vin), new ServiceCompany(autoRepository.get(vin)));
    }
    @Override
    public List<String> getAllAuto(){
        List<String> result = new ArrayList<>(autoRepository.getAllAutoVin());
        return result;
    };
    @Override
    public boolean doesCarExist(String autoVin)
    {
        boolean resulat = false;
        for(int i = 0; i<=autoRepository.getAllAutoVin().size()-1; i++)
        {
            if(autoRepository.getAllAutoVin().get(i).equals(autoVin))
            {
                resulat = true;
            }
        }
        return resulat;
    }
    @Override
    public boolean doesCarToServiceCompany(String autoVin, String nameServiceCompany)
    {
        Auto newAuto = new Auto(autoVin, nameServiceCompany, new ServiceCompany(nameServiceCompany));
        return autoRepository.doesCarToServiceCompanyRepository(newAuto);
    }

    @Override
    public boolean updateAuto(String vin, String newNameServiceCompany) {
        Auto updateAuto = new Auto(vin, newNameServiceCompany, new ServiceCompany(newNameServiceCompany));
        return autoRepository.update(updateAuto);
    }
}


interface GetInfoAuto{
    Auto getAuto(String vin);
    List<String> getAllAuto();
    boolean doesCarExist(String autoVin);
    boolean doesCarToServiceCompany(String autoVin, String nameServiceCompany);
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