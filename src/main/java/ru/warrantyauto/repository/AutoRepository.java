package ru.warrantyauto.repository;

import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoRepository implements Repository<AutoEntity, String>, RepositoryAuto{

    private static Connection connection;
    DBConnectionProvider dbConnectionProvider;
    ServiceCompanyRepository serviceCompanyRepository;
    public AutoRepository(DBConnectionProvider newDBConnectionProvider)
    {
        dbConnectionProvider = newDBConnectionProvider;
        serviceCompanyRepository = new ServiceCompanyRepository(newDBConnectionProvider);
        createCustomersTableIfNotExistsAuto();
    }
    @Override
    public boolean create(AutoEntity auto){
        boolean result = false;
        //DriverManager.getConnection(url, user, password)
        try(Connection conn = dbConnectionProvider.getConnection())
        {
            Statement statement = conn.createStatement();
            String autoVin = auto.getVin();
            String nameServiceCompany = auto.getNameServiceCompany();
            result = true;
            System.out.println("INSERT INTO \"Auto\" (\"Vin\", \"NameServiceCompany\") VALUES (" +"'" + autoVin+"'" + ", " + "'" + nameServiceCompany + "'" + ")");
            statement.executeUpdate("INSERT INTO \"Auto\" (\"Vin\", \"NameServiceCompany\") VALUES (" +"'" + autoVin+"'" + ", " + "'" + nameServiceCompany + "'" + ")");
        }
        catch(Exception ex){
        }
        return result;
    };
    @Override
    public boolean update(AutoEntity auto)
    {
        boolean result = false;
        //getPostgresConnection();
        try(Connection conn = dbConnectionProvider.getConnection())
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
    public boolean delete(AutoEntity auto)
    {
        //getPostgresConnection();
        try(Connection conn = dbConnectionProvider.getConnection())
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
        try(Connection conn = dbConnectionProvider.getConnection())
        {
            Statement statement = conn.createStatement();

            String sql= "SELECT \"NameServiceCompany\" FROM \"Auto\" WHERE \"Vin\" = '" + vin + "'";
            System.out.println(sql);
            statement.executeQuery(sql).toString();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                res = rs.getString(1);
            }

        }
        catch(Exception ex){

        }
        return res;
    }
    @Override
    public List<String> getAllAutoServiceCompany()
    {
        List<String> result = new ArrayList<>();
        try(Connection conn = dbConnectionProvider.getConnection())
        {
            Statement statement = conn.createStatement();
            String sql= "SELECT \"NameServiceCompany\" FROM \"Auto\"";

            ResultSet rs = statement.executeQuery(sql);
            System.out.println("12231231231");
            while (rs.next())
            {
                result.add(rs.getString("NameServiceCompany"));
            }
        }
        catch(Exception ex){

        }
        return result;
    }

    @Override
    public List<String> getAllAutoVin() {
        try(Connection conn = dbConnectionProvider.getConnection())
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
        //getPostgresConnection();
        try(Connection conn = dbConnectionProvider.getConnection())
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

    public boolean doesCarToServiceCompanyRepository(AutoEntity auto)
    {
        ArrayList<String> allAutoVin = new ArrayList<>(serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompanyEntity(auto.getNameServiceCompany())));
        System.out.println(auto.getNameServiceCompany());
        System.out.println(allAutoVin.size());
        boolean result = false;
        if(allAutoVin.size()-1>0)
        {
            for(int i =0; i<=allAutoVin.size()-1; i++)
            {
                System.out.print(allAutoVin.get(i).equals(auto.getVin()));
                if (allAutoVin.get(i).equals(auto.getVin()))
                {
                    result = true;
                    return result;
                }
            }
        }

       return false;
    }
    private void createCustomersTableIfNotExistsAuto()
    {
        try (Connection conn = this.dbConnectionProvider.getConnection())
        {
            PreparedStatement pstmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS public.\"Auto\"\n" +
                    "(\n" +
                    "    \"Vin\" character varying(17) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    \"NameServiceCompany\" character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    CONSTRAINT \"Auto_pkey\" PRIMARY KEY (\"Vin\")\n" +
                    ")");
            pstmt.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
