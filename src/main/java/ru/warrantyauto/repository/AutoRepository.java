package ru.warrantyauto.repository;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.database.DataBase;

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
        getPostgresConnection();
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
    public Auto read(String vin)
    {
        return null;
    }
    @Override
    public boolean update(Auto auto)
    {
        boolean result = false;
        getPostgresConnection();
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
        getPostgresConnection();
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
        getPostgresConnection();
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
        getPostgresConnection();
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

        getPostgresConnection();
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
            return result;
        }
        catch(Exception ex){

        }
        return null;
    }
    public boolean deleteAll()
    {
        boolean result = false;
        getPostgresConnection();
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








public ServiceCompanyRepository getServiceCompanyRepository()
{
    return serviceCompanyRepository;
}

    public void addAutoToRepository(String newVin, String nameServiceCompany)
    {
        ServiceCompany currentServiceCompany = serviceCompanyRepository.getServiceCompanyToName(nameServiceCompany);
        Auto newAuto = new Auto(newVin, nameServiceCompany, currentServiceCompany);
        DataBase.getInstance().add(newAuto, nameServiceCompany);
    }
    public boolean doesCarExistToRepository(String autoVin)
    {
        return DataBase.getInstance().addVin(autoVin);
    }
    public boolean doesCarToServiceCompanyRepository(String autoVin)
    {
        return serviceCompanyRepository.doesCarToServiceCompanyRepository(autoVin);
    }
    public void deleteAutoFromServiceCompany(String nameServiceCompany)
    {
        for(int i = 0; i<= DataBase.getInstance().getAuto().size()-1; i++)
        {
            if (DataBase.getInstance().getAuto().get(i).getVin().equals(nameServiceCompany))
            {
                DataBase.getInstance().getAuto().remove(i);
            }
        }
    }
}
