package ru.warrantyauto.sevice;

import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoService implements DeleteAuto, AddAuto, GetInfoAuto, UpdateAuto {

//    String url = "jdbc:postgresql://localhost:5432/auto_dealer";
//    String user = "postgres";
//    String password = "2112";

    DBConnectionProvider dbConnectionProvider;
    final AutoRepository autoRepository;
    final ServiceCompanyRepository  serviceCompanyRepository;
    public AutoService(DBConnectionProvider newDbConnectionProvider)
    {
        dbConnectionProvider = newDbConnectionProvider;
        autoRepository = new AutoRepository(newDbConnectionProvider);
        serviceCompanyRepository = new ServiceCompanyRepository(newDbConnectionProvider);
    }
    @Override
    public boolean deleteAuto(String vin)
    {
        AutoDTO deleteAuto = new AutoDTO(vin, autoRepository.get(vin), new ServiceCompanyDTO(autoRepository.get(vin)));
        serviceCompanyRepository.deleteVinToServiceCompany(deleteAuto);
        return autoRepository.delete(deleteAuto);
    }
    @Override
    public boolean addAuto(String newVin, String nameServiceCompany)
    {
        boolean res = false;
        ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository(dbConnectionProvider);
        Set<String> setServiceCompany = new HashSet<>(serviceCompanyRepository.getAllServiceCompany());
        if(!setServiceCompany.add(nameServiceCompany))
        {
            AutoDTO newAuto = new AutoDTO(newVin, nameServiceCompany, new ServiceCompanyDTO(nameServiceCompany));
            autoRepository.create(newAuto);
            res = serviceCompanyRepository.addVinToServiceCompany(newAuto);
        }
        return res;
    }

    @Override
    public String getServiceCompanyToVin(String vin) {
        return autoRepository.get(vin);
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
        AutoDTO newAuto = new AutoDTO(autoVin, nameServiceCompany, new ServiceCompanyDTO(nameServiceCompany));
        return autoRepository.doesCarToServiceCompanyRepository(newAuto);
    }

    @Override
    public boolean updateAuto(String vin, String newNameServiceCompany) {
        String deleteVinSC = this.getServiceCompanyToVin(vin);
        AutoDTO deleteAuto = new AutoDTO(vin, deleteVinSC, new ServiceCompanyDTO(deleteVinSC));
        AutoDTO updateAuto = new AutoDTO(vin, newNameServiceCompany, new ServiceCompanyDTO(newNameServiceCompany));
        serviceCompanyRepository.addVinToServiceCompany(updateAuto);
        serviceCompanyRepository.deleteVinToServiceCompany(deleteAuto);
        return autoRepository.update(updateAuto);
    }
}


interface GetInfoAuto{
    String getServiceCompanyToVin(String vin);
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