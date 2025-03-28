package ru.warrantyauto.repository;

import ru.warrantyauto.config.DBConnectionProvider;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoRepository implements Repository<AutoEntity, String>, RepositoryAuto{

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
            String autoVin = auto.getVin();
            String nameServiceCompany = auto.getNameServiceCompany();
            String sql = "INSERT INTO \"Auto\" (\"Vin\", \"NameServiceCompany\") VALUES ( ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, autoVin);
            preparedStatement.setString(2, nameServiceCompany);
            result = true;
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("AutoRepository Exception::create");
        }
        return result;
    }
    
    @Override
    public boolean update(AutoEntity auto) {
        boolean result = false;
        try (Connection conn = dbConnectionProvider.getConnection()) {
            String sql = "UPDATE  \"Auto\" SET \"NameServiceCompany\" = ? WHERE \"Vin\" = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, auto.getNameServiceCompany());
            preparedStatement.setString(2, auto.getVin());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception ex) {
            System.out.println("AutoRepository Exception::update");
        }
        return result;
    }

    @Override
    public boolean delete(AutoEntity auto) {
        try (Connection conn = dbConnectionProvider.getConnection()) {
            String sql = "DELETE FROM \"Auto\" WHERE \"Vin\" = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, auto.getVin());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("AutoRepository Exception::delete");
        }
        return false;
    }

    @Override
    public String get(String vin) {
        String res = null;
        try(Connection conn = dbConnectionProvider.getConnection())
        {
            String sql1 = "SELECT \"NameServiceCompany\" FROM \"Auto\" WHERE \"Vin\" = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.setString(1, vin);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                res = rs.getString(1);
            }
        }
        catch(Exception ex){
            System.out.println("AutoRepository Exception::get");
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
            System.out.println("AutoRepository Exception::getAllAutoServiceCompany");
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
            System.out.println("AutoRepository Exception::getAllAutoVin");
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
            System.out.println("AutoRepository Exception::deleteAll");
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
