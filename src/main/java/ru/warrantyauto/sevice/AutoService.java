package ru.warrantyauto.sevice;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.model.Model;
import ru.warrantyauto.repository.AutoRepository;

public class AutoService implements DeleteAuto, AddAuto, GetInfoAuto {
    AutoRepository autoRepository = new AutoRepository();

    @Override
    public void deleteAuto()
    {

    }
    @Override
    public void addAuto(String newVin, String nameServiceCompany)
    {
        autoRepository.addAutoToRepository(newVin, nameServiceCompany);
    }
    @Override
    public Auto getAuto()
    {
        return null;
    }
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
}

interface GetInfoAuto{
    default Auto getAuto()
    {
        return null;
    }
    default boolean doesCarExist(String autoVin)
    {
        return false;
    }
    default boolean doesCarToServiceCompany(String autoVin)
    {
        return false;
    }
}
interface DeleteAuto {
    default void deleteAuto()
    {
    }
}
interface AddAuto{
    default void addAuto(String newVin, String nameServiceCompany)
    {
    }
}