package ru.warrantyauto.entities;

import java.util.ArrayList;

public class ServiceCompany extends Entity {

    String name;
    ArrayList<Auto> allAuto = new ArrayList<>();
    ArrayList<String> allVin = new ArrayList<>();

    public ServiceCompany(String newName)
    {
        this.name = newName;
    }
    public String getName()
    {
        return name;
    }
    public void addAuto(Auto newAuto)
    {
        allAuto.add(newAuto);
        allVin.add(newAuto.getVinCode());
    }
    public void setAllVin(ArrayList<String> newVin)
    {
        allVin.addAll(newVin);
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
