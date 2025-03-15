package ru.warrantyauto.repository;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.database.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ServiceCompanyRepository implements Repository<ServiceCompany, List<String>>, RepositoryServiceCompany{

    static Connection connection;
    String url = "jdbc:postgresql://localhost:5432/auto_dealer";
    String user = "postgres";
    String password = "2112";

    @Override
    public boolean create(ServiceCompany serviceCompany)
    {
        boolean result = false;
        getPostgresConnection();
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String SeviceCompanyName = serviceCompany.getName();
            List<String> vinToServicCompany = this.getAllAutoToServiceCompany(serviceCompany);
            vinToServicCompany.addAll(serviceCompany.allVinAuto());
            for (int i = 0; i<=vinToServicCompany.size()-1; i++)
            {
                vinToServicCompany.set(i, "\"" + vinToServicCompany.get(i) + "\"");
            }
            String sqlArray = vinToServicCompany.toString().replace(']', '}');
            sqlArray = sqlArray.replace('[', '{');
            result = true;
            String sql = "INSERT INTO \"ServiceCompany\"(\"ServiceCompany\", \"VinList\") VALUES ('" + SeviceCompanyName + "'," + "'" + sqlArray + "')" ;
            statement.executeUpdate(sql);
        }
        catch(Exception ex){
        }
        return result;

    };
    @Override
    public boolean update(ServiceCompany serviceCompany)
    {
        boolean result = false;
        getPostgresConnection();
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String serviceCompanyName = serviceCompany.getName();
            ArrayList<String> newVinListServiceCompany = new ArrayList<>();
            newVinListServiceCompany.addAll(serviceCompany.allVinAuto());
            for (int i = 0; i<=newVinListServiceCompany.size()-1; i++)
            {
                newVinListServiceCompany.set(i, "\"" + newVinListServiceCompany.get(i) + "\"");
            }
            String updateSQLVinList = newVinListServiceCompany.toString().replace(']', '}');
            updateSQLVinList = updateSQLVinList.replace('[', '{');
            System.out.println(updateSQLVinList);

            String sql = "UPDATE \"ServiceCompany\" SET \"VinList\" = '" + updateSQLVinList +"'" + "WHERE \"ServiceCompany\" = '" + serviceCompanyName + "'\n";
            System.out.println(sql);
            statement.executeUpdate(sql);
            result = true;
        }
        catch(Exception ex){
        }
        return result;
    }
    @Override
    public boolean delete(ServiceCompany serviceCompany)
    {
        boolean result = false;
        getPostgresConnection();
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String serviceCompanyName = serviceCompany.getName();
            String sql = "DELETE FROM \"ServiceCompany\" WHERE \"ServiceCompany\" =" + "'" + serviceCompanyName + "'";
            System.out.println(sql);
            statement.executeUpdate(sql);
            result = true;
        }
        catch(Exception ex){
        }
        return result;
    }



    public static Connection getPostgresConnection()
    {
        try {
            DriverManager.registerDriver((Driver)
                    Class.forName("org.postgresql.Driver").newInstance());

            String url = "jdbc:postgresql://localhost:5432/auto_dealer";
            String user = "postgres";
            String password = "2112";

            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getAllAutoToServiceCompany(ServiceCompany serviceCompany) {

        List<String> res = new ArrayList<>();
        String stringResult = null;
        getPostgresConnection();
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String nameServiceCompany = serviceCompany.getName();
            String sql= "SELECT \"VinList\" FROM \"ServiceCompany\" WHERE \"ServiceCompany\" = '" + nameServiceCompany + "'";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                String resString = rs.getArray(1).toString();
                resString = resString.replace('{', ' ');
                resString = resString.replace('}', ' ');
                resString = resString.trim();
                String[] arrayList = resString.split(",");
                Collections.addAll(res, arrayList);
            }


        }
        catch(Exception ex){

        }
        return res;

    }









    public String getNameServiceCompanyRepository(String nameServiceCompany)
    {
        for (int i = 0; i<= DataBase.getInstance().getServiceCompany().size()-1; i++)
        {
            if(DataBase.getInstance().getServiceCompany().get(i).equals(nameServiceCompany))
            {
                return DataBase.getInstance().getServiceCompans().get(i).toString();
            }
        }
        return null;
    }

    public String getAllVinServiceCompanyRepository(String nameServiceCompany)
    {
        for (int i = 0; i<= DataBase.getInstance().getServiceCompany().size()-1; i++)
        {
            if(DataBase.getInstance().getServiceCompany().get(i).equals(nameServiceCompany))
            {
                return DataBase.getInstance().getServiceCompans().get(i).allVinAuto().toString();
            }
        }
        return null;
    }

    public void addServiceCompanyRepository(ServiceCompany serviceCompany)
    {
        DataBase.getInstance().addServiceCompans(serviceCompany);
    }

    public boolean doesCarToServiceCompanyRepository(String autoVin)
    {
        return !DataBase.getInstance().addServiceCompanyString(autoVin);
    }

    public ServiceCompany getServiceCompanyToName(String nameServiceCompany)
    {

        ServiceCompany result = null;

        for(int i = 0; i<= DataBase.getInstance().getServiceCompans().size()-1; i++)
        {
            if(DataBase.getInstance().getServiceCompans().get(i).getName().equals(nameServiceCompany))
            {
                result = DataBase.getInstance().getServiceCompans().get(i);
                return result;
            }
        }
        return result;
    }

    public List<String> getAllServiceCompanyToRepository() {

        return DataBase.getInstance().getServiceCompany();
    }

    public boolean addServiceCompanySet(String newServiceCompany)
    {
        return DataBase.getInstance().addServiceCompanyString(newServiceCompany);
    }

    public boolean deleteServiceCompanyRepository(String deleteCompany)
    {
        for(int i = 0; i<= DataBase.getInstance().getAuto().size()-1; i++)
        {
            if (DataBase.getInstance().getAuto().get(i).getNameServiceCompany().equals(deleteCompany))
            {
                DataBase.getInstance().getAuto().remove(i);
            }
        }
        return DataBase.getInstance().deleteServiceCompany(deleteCompany);
    }


}
