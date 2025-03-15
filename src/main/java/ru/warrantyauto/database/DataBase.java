package ru.warrantyauto.database;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class DataBase {
    private static DataBase instance = new DataBase();

    private List<Auto> auto;
    private List<ServiceCompany> serviceCompans;
    private Set<String> vinList;
    private Set<String> nameServiceCompany;
    private HashMap<String, String> vinAndServiceCompany;
    

    public static DataBase getInstance() {
        return instance;
    }

    private DataBase() {
        this.auto = new ArrayList<>();
        this.vinList = new HashSet<>();
        this.serviceCompans = new ArrayList<>();
        this.nameServiceCompany = new HashSet<>();
        this.vinAndServiceCompany = new HashMap<>();
        //getPostgresConnection().getCatalog()

    }
    public static Connection getPostgresConnection()
    {
        try {
            DriverManager.registerDriver((Driver)
                    Class.forName("org.postgresql.Driver").newInstance());

            String url = "jdbc:postgresql://localhost:5432/auto_dealer";
            String user = "postgres";
            String password = "2112";
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
                .map(Auto::getVin)
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
