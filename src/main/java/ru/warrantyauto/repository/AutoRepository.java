package ru.warrantyauto.repository;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoRepository implements Repository<Auto, String>, RepositoryAuto{

    ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
    private static Connection connection;
    //private Statement statement;
    String url = "jdbc:postgresql://localhost:5432/auto_dealer";
    String user = "postgres";
    String password = "2112";

    @Override
    public boolean create(Auto auto){
        boolean result = false;
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String autoVin = auto.getVin();
            String nameServiceCompany = auto.getNameServiceCompany();
            result = true;
            //System.out.println("INSERT INTO \"Auto\" (\"Vin\", \"NameServiceCompany\") VALUES (" +"'" + autoVin+"'" + ", " + "'" + nameServiceCompany + "'" + ")");
            statement.executeUpdate("INSERT INTO \"Auto\" (\"Vin\", \"NameServiceCompany\") VALUES (" +"'" + autoVin+"'" + ", " + "'" + nameServiceCompany + "'" + ")");

        }
        catch(Exception ex){
        }
        return result;
    };
    @Override
    public boolean update(Auto auto)
    {
        boolean result = false;
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String autoVin = auto.getVin();
            String newNameServiceCompany = auto.getNameServiceCompany();

            String sql = "UPDATE  \"Auto\" SET \"NameServiceCompany\" =" + "'" + newNameServiceCompany +"' " + "WHERE \"Vin\" = '" + autoVin + "'";
            statement.executeUpdate(sql);
            result = true;
        }
        catch(Exception ex){
        }
        return result;
    }
    @Override
    public boolean delete(Auto auto)
    {
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String autoVin = auto.getVin();
            statement.executeUpdate("DELETE FROM \"Auto\" WHERE \"Vin\" = '" + autoVin +"'");
            return true;
        }
        catch(Exception ex){
        }
        return false;
    }
    @Override
    public String get(String vin)
    {
        String res = null;
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String sql= "SELECT \"NameServiceCompany\" FROM \"Auto\" WHERE \"Vin\" = '" + vin + "'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                return rs.getString(1);
            }

        }
        catch(Exception ex){

        }
        return res;
    }
    @Override
    public List<String> getAllAutoServiceCompany()
    {
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String sql= "SELECT \"NameServiceCompany\" FROM \"Auto\"";
            ResultSet rs = statement.executeQuery(sql);
            List<String> result = new ArrayList<>();
            while (rs.next())
            {
                result.add(rs.getString("NameServiceCompany"));
                //System.out.println(rs.getString("NameServiceCompany"));
            }
            return result;
        }
        catch(Exception ex){

        }
        return null;
    }

    @Override
    public List<String> getAllAutoVin() {

        try(Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement statement = conn.createStatement();
            String sql= "SELECT \"Vin\" FROM \"Auto\"";
            ResultSet rs = statement.executeQuery(sql);
            List<String> result = new ArrayList<>();
            while (rs.next())
            {
                result.add(rs.getString("Vin"));
            }
            System.out.println(result);
            return result;
        }
        catch(Exception ex){

        }
        return null;
    }
    public boolean deleteAll()
    {
        boolean result = false;
        try(Connection conn = DriverManager.getConnection(url, user, password))
        {

            Statement statement = conn.createStatement();
            String sql= "DELETE FROM \"Auto\"";
            result = true;
            ResultSet rs = statement.executeQuery(sql);

        }
        catch(Exception ex){

        }
        return result;
    }

    public boolean doesCarToServiceCompanyRepository(Auto auto)
    {
        ArrayList<String> allAutoVin = new ArrayList<>(serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompany(auto.getNameServiceCompany())));
        System.out.println(allAutoVin);
        boolean result = false;
        for(int i =0; i<=allAutoVin.size()-1; i++)
        {
            if (allAutoVin.get(i).equals(auto.getVin()))
            {
                result = true;
            }
        }
        return result;
    }
}
