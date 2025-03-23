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
    CreateTable createTable;

    public AutoRepository(DBConnectionProvider newDBConnectionProvider) {
        this.dbConnectionProvider = newDBConnectionProvider;
        this.serviceCompanyRepository = new ServiceCompanyRepository(newDBConnectionProvider);
        createTable = new CreateTable(dbConnectionProvider);
        createTable.createCustomersTableIfNotExistsAuto();
    }

    @Override
    public boolean create(AutoEntity auto) {
        boolean result = false;
        try (Connection conn = dbConnectionProvider.getConnection()) {
            Statement statement = conn.createStatement();
            String autoVin = auto.getVin();
            String nameServiceCompany = auto.getNameServiceCompany();
            String sql = "INSERT INTO \"Auto\" (\"Vin\", \"NameServiceCompany\") VALUES (" + "'" + autoVin + "'" + ", " + "'" + nameServiceCompany + "'" + ")";
            result = true;
            statement.executeUpdate(sql);
        } catch (Exception ex) {
        }
        return result;
    }

    ;

    @Override
    public boolean update(AutoEntity auto) {
        boolean result = false;
        try (Connection conn = dbConnectionProvider.getConnection()) {
            Statement statement = conn.createStatement();
            String autoVin = auto.getVin();
            String newNameServiceCompany = auto.getNameServiceCompany();

            String sql = "UPDATE  \"Auto\" SET \"NameServiceCompany\" =" + "'" + newNameServiceCompany + "' " + "WHERE \"Vin\" = '" + autoVin + "'";
            statement.executeUpdate(sql);
            result = true;
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public boolean delete(AutoEntity auto) {
        try (Connection conn = dbConnectionProvider.getConnection()) {
            Statement statement = conn.createStatement();
            String autoVin = auto.getVin();
            statement.executeUpdate("DELETE FROM \"Auto\" WHERE \"Vin\" = '" + autoVin + "'");
            return true;
        } catch (Exception ex) {
        }
        return false;
    }

    @Override
    public String get(String vin) {
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
    public List<String> getAllAutoServiceCompany() {
        List<String> result = new ArrayList<>();
        try (Connection conn = dbConnectionProvider.getConnection()) {
            Statement statement = conn.createStatement();
            String sql = "SELECT \"NameServiceCompany\" FROM \"Auto\"";

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString("NameServiceCompany"));
            }
        } catch (Exception ex) {

        }
        return result;
    }

    @Override
    public List<String> getAllAutoVin() {
        try (Connection conn = dbConnectionProvider.getConnection()) {
            Statement statement = conn.createStatement();
            String sql = "SELECT \"Vin\" FROM \"Auto\"";
            ResultSet rs = statement.executeQuery(sql);
            List<String> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getString("Vin"));
            }
            return result;
        } catch (Exception ex) {

        }
        return null;
    }

    public boolean deleteAll() {
        boolean result = false;
        try (Connection conn = dbConnectionProvider.getConnection()) {

            Statement statement = conn.createStatement();
            String sql = "DELETE FROM \"Auto\"";
            result = true;
            ResultSet rs = statement.executeQuery(sql);

        } catch (Exception ex) {

        }
        return result;
    }

    public boolean doesCarToServiceCompanyRepository(AutoEntity auto) {
        ArrayList<String> allAutoVin = new ArrayList<>(serviceCompanyRepository.getAllAutoToServiceCompany(new ServiceCompanyEntity(auto.getNameServiceCompany())));
        boolean result = false;
        if (allAutoVin.size() - 1 > 0) {
            for (int i = 0; i <= allAutoVin.size() - 1; i++) {
                System.out.print(allAutoVin.get(i).equals(auto.getVin()));
                if (allAutoVin.get(i).equals(auto.getVin())) {
                    result = true;
                    return result;
                }
            }
        }

        return false;
    }
}
