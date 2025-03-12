package ru.warrantyauto.model;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;

import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();

    private List<Auto> auto;
    private List<ServiceCompany> serviceCompans;
    private Set<String> vinList;
    private Set<String> nameServiceCompany;
    private HashMap<String, String> vinAndServiceCompany;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        this.auto = new ArrayList<>();
        this.vinList = new HashSet<>();
        this.serviceCompans = new ArrayList<>();
        this.nameServiceCompany = new HashSet<>();
        this.vinAndServiceCompany = new HashMap<>();
    }

    public void addServiceCompans(ServiceCompany newServiceCompan)
    {
        serviceCompans.add(newServiceCompan);
    }
    public HashMap<String, String> getVinAndServiceCompany()
    {
        return vinAndServiceCompany;
    }
    public void addVinAndServiceCompany(String newVin, String newSC)
    {
        vinAndServiceCompany.put(newVin, newSC);
    }
    public List<ServiceCompany> getServiceCompans()
    {
        return serviceCompans;
    }
    public List<Auto> getAuto()
    {
        return auto;
    }
    public void add(Auto newAuto, String nameServiceCompany) {
        auto.add(newAuto);
        for(int i = 0; i<=serviceCompans.size()-1; i++)
        {
            if(serviceCompans.get(i).toString().equals(nameServiceCompany))
            {
                serviceCompans.get(i).addAuto(newAuto);
            };
        }
        serviceCompans.size();
    }
    public boolean addServiceCompanyString(String newServiceCompany)
    {;
        return nameServiceCompany.add(newServiceCompany);
    }
    public boolean addVin(String newVin)
    {
        return vinList.add(newVin);
    }
    public List<String> getServiceCompany() {
        List<String> res = new ArrayList<>();
        for(int i =0; i<= serviceCompans.size()-1; i++)
        {
            res.add(serviceCompans.get(i).toString());
        }
        return res;
    }
    public List<String> getStringAuto() {
        return auto.stream()
                .map(Auto::getName)
                .collect(Collectors.toList());
    }
    public boolean deleteServiceCompany(String nameServiceCompany)
    {
        for(int i = 0; i <=serviceCompans.size()-1;i++)
        {
            if(serviceCompans.get(i).getName().equals(nameServiceCompany))
            {
                serviceCompans.remove(i);
                return true;
            }
        }
        return false;
    }
}
