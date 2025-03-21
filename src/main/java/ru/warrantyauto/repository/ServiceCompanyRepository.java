package ru.warrantyauto.repository;

import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceCompanyRepository implements Repository<ServiceCompany, List<String>>, RepositoryServiceCompany{

    static Connection connection;
    String url = "jdbc:postgresql://localhost:5432/auto_dealer";
    String user = "postgres";
    String password = "2112";
    DBConnectionProvider dbConnectionProvider;
    public ServiceCompanyRepository(DBConnectionProvider newDbConnectionProvider)
    {
        dbConnectionProvider = newDbConnectionProvider;
        createCustomersTableIfNotExistsServiceCompany();
    }
    @Override
    public boolean create(ServiceCompany serviceCompany)
    {
        boolean result = false;
        getPostgresConnection();
        try(Connection conn = dbConnectionProvider.getConnection())
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
            System.out.println(sql);
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
        try(Connection conn = dbConnectionProvider.getConnection())
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
            String sql = "UPDATE \"ServiceCompany\" SET \"VinList\" = '" + updateSQLVinList +"'" + "WHERE \"ServiceCompany\" = '" + serviceCompanyName + "'\n";
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
        try(Connection conn = dbConnectionProvider.getConnection())
        {
            Statement statement = conn.createStatement();
            String serviceCompanyName = serviceCompany.getName();
            String sql = "DELETE FROM \"ServiceCompany\" WHERE \"ServiceCompany\" =" + "'" + serviceCompanyName + "'";
            statement.executeUpdate(sql);
            result = true;
        }
        catch(Exception ex){
        }
        return result;
    }



    private static Connection getPostgresConnection()
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

        try(Connection conn = dbConnectionProvider.getConnection())
        {
            Statement statement = conn.createStatement();
            String nameServiceCompany = serviceCompany.getName();
            String sql= "SELECT \"VinList\" FROM \"ServiceCompany\" WHERE \"ServiceCompany\" = '" + nameServiceCompany + "'";
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
    public boolean addAutoToServiceCompany(Auto newAuto)
    {
        boolean res = false;

        try(Connection conn = dbConnectionProvider.getConnection())
        {
            Statement statement = conn.createStatement();
            String nameServiceCompany = newAuto.getNameServiceCompany();
            List<String> vinToServicCompany = this.getAllAutoToServiceCompany(new ServiceCompany(nameServiceCompany));
            vinToServicCompany.add(newAuto.getVin());
            this.delete(new ServiceCompany(nameServiceCompany));
            this.create(new ServiceCompany(nameServiceCompany));
            res = true;
            //ResultSet rs = statement.executeQuery(sql);
        }
        catch(Exception ex){
        }
        return res;
    }
    @Override
    public List<String> getAllServiceCompany() {
        List<String> res = new ArrayList<>();
        String stringResult = null;

        try(Connection conn = dbConnectionProvider.getConnection())
        {
            Statement statement = conn.createStatement();
            String sql= "SELECT \"ServiceCompany\" FROM \"ServiceCompany\"";
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
    public boolean deleteAllServiceCompany()
    {
        boolean res = false;

        try(Connection conn = dbConnectionProvider.getConnection())
        {
            Statement statement = conn.createStatement();
            String sql= "DELETE FROM \"ServiceCompany\"";
            res = true;
            ResultSet rs = statement.executeQuery(sql);
        }
        catch(Exception ex){
        }
        return res;
    }

    public ServiceCompany getServiceCompanyToName(String nameServiceCompany)
    {
        ServiceCompany result = null;
        for(int i = 0; i<= this.getAllServiceCompany().size()-1; i++)
        {
            getAllServiceCompany().get(i).equals(nameServiceCompany);
            result = new ServiceCompany(nameServiceCompany);
        }
        return result;
    }
    public boolean addVinToServiceCompany(Auto newAuto)
    {
        ServiceCompany cahsServiceCompany = new ServiceCompany(newAuto.getNameServiceCompany());
        ArrayList<String> cashVin = new ArrayList<>(this.getAllAutoToServiceCompany(cahsServiceCompany));
        this.delete(getServiceCompanyToName(newAuto.getNameServiceCompany()));
        ArrayList<String> newVinList = new ArrayList<>();
        if(cashVin.size()!=0)
        {
            if(cashVin.get(0).equals(""))
            {
                newVinList.add(newAuto.getVin());
            }
            else
            {
                for(int i = 0; i<=cashVin.size()-1; i++)
                {
                    if(!cashVin.get(i).equals(""))
                    {
                        newVinList.add(cashVin.get(i));
                    }
                }
                newVinList.add(newAuto.getVin());
            }
        }
        else
        {
            newVinList.add(newAuto.getVin());
        }
        cahsServiceCompany.setAllVin(newVinList);
        return this.create(cahsServiceCompany);
    }
    public boolean deleteVinToServiceCompany(Auto newAuto) {
        boolean result =false;
        ServiceCompany cahsServiceCompany = new ServiceCompany(newAuto.getNameServiceCompany());
        ArrayList<String> cashVin = new ArrayList<>(this.getAllAutoToServiceCompany(cahsServiceCompany));
        this.delete(getServiceCompanyToName(newAuto.getNameServiceCompany()));
        ArrayList<String> newVinList = new ArrayList<>();
        for(int i = 0; i<=cashVin.size()-1; i++)
        {
            if(cashVin.get(i).equals(newAuto.getVin()))
            {
                cashVin.remove(i);
                result = true;
            }
        }
        cahsServiceCompany.setAllVin(cashVin);
        this.create(cahsServiceCompany);
        return result;
    }
    public boolean updateServiceCompany(ServiceCompany oldCompany, ServiceCompany newCompany)
    {
        ArrayList<String> cashBaseVin = new ArrayList<>(this.getAllAutoToServiceCompany(new ServiceCompany(oldCompany.getName())));
        this.delete(oldCompany);
        newCompany.setAllVin(cashBaseVin);
        this.create(newCompany);
        return true;
    }
    private void createCustomersTableIfNotExistsServiceCompany(){
        try (Connection conn = this.dbConnectionProvider.getConnection())
        {
            PreparedStatement pstmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS \"ServiceCompany\"(\n" +
                            "    \"ServiceCompany\" character varying NOT NULL primary key,\n" +
                            "    \"VinList\" text[]\n" +
                            ")");
            pstmt.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
