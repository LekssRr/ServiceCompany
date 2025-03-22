package ru.warrantyauto.entity;

import java.util.ArrayList;

public class ServiceCompanyEntity extends Entity {

    String name;
    ArrayList<AutoEntity> allAuto = new ArrayList<>();
    ArrayList<String> allVin = new ArrayList<>();

    public ServiceCompanyEntity(String newName)
    {
        this.name = newName;
        for(int i = 0; i<=allVin.size()-1; i++)
        {
            allAuto.add(new AutoEntity(allVin.get(i), this.getName(), this));
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
            allAuto.add(new AutoEntity(allVin.get(i), this.getName(), this));
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
