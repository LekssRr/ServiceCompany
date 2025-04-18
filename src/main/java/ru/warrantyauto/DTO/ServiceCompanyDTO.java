package ru.warrantyauto.DTO;

import java.util.ArrayList;

public class ServiceCompanyDTO {
    String name;
    ArrayList<AutoDTO> allAuto = new ArrayList<>();
    ArrayList<String> allVin = new ArrayList<>();

    public ServiceCompanyDTO(String newName)
    {
        this.name = newName;
        for(int i = 0; i<=allVin.size()-1; i++)
        {
            allAuto.add(new AutoDTO(allVin.get(i), this.getName()));
        }
    }
    public String getName()
    {
        return name;
    }
    public void setAllVin(ArrayList<String> newVin)
    {
        allVin.addAll(newVin);
        for(int i = 0; i<=allVin.size()-1; i++)
        {
            allAuto.add(new AutoDTO(allVin.get(i), this.getName()));
        }
    }
    public ArrayList<String> allVinAuto()
    {
        return allVin;
    }
    @Override
    public String toString()
    {
        return name;
    }
}
