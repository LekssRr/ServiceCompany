package ru.warrantyauto.repository;

import ru.warrantyauto.config.DBConnectionProvider;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceCompanyRepository implements Repository<ServiceCompanyEntity, List<String>>, RepositoryServiceCompany{

    DBConnectionProvider dbConnectionProvider;
    CreateTable createTable;

    public ServiceCompanyRepository(DBConnectionProvider newDbConnectionProvider) {
        this.dbConnectionProvider = newDbConnectionProvider;
        createTable = new CreateTable(newDbConnectionProvider);
        createTable.createCustomersTableIfNotExistsServiceCompany();
    }

    @Override
    public boolean create(ServiceCompanyEntity serviceCompany) {
        boolean result = false;
        try (Connection conn = dbConnectionProvider.getConnection()) {
            String SeviceCompanyName = serviceCompany.getName();
            String sql = "INSERT INTO \"ServiceCompany\"(\"ServiceCompany\", \"VinList\") VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, SeviceCompanyName);
            preparedStatement.setArray(2, conn.createArrayOf("text", serviceCompany.allVinAuto().toArray()));
            result = true;
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ServiceCompanyRepository Exception::create");
        }
        return result;

    }

    @Override
    public boolean update(ServiceCompanyEntity serviceCompany) {
        boolean result = false;
        try (Connection conn = dbConnectionProvider.getConnection()) {
            String serviceCompanyName = serviceCompany.getName();
            String sql1 = "UPDATE \"ServiceCompany\" SET \"VinList\" = ? WHERE \"ServiceCompany\" = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.setArray(1, conn.createArrayOf("text", serviceCompany.allVinAuto().toArray()));
            preparedStatement.setString(2, serviceCompanyName);
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception ex) {
            System.out.println("ServiceCompanyRepository Exception::update");
        }
        return result;
    }

    @Override
    public boolean delete(ServiceCompanyEntity serviceCompany) {
        boolean result = false;
        try (Connection conn = dbConnectionProvider.getConnection()) {
            String sql = "DELETE FROM \"ServiceCompany\" WHERE \"ServiceCompany\" = ?";
            String serviceCompanyName = serviceCompany.getName();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, serviceCompanyName);
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception ex) {
            System.out.println("ServiceCompanyRepository Exception::delete");
        }
        return result;
    }


    @Override
    public List<String> getAllAutoToServiceCompany(ServiceCompanyEntity serviceCompany) {

        List<String> res = new ArrayList<>();
        try (Connection conn = dbConnectionProvider.getConnection()) {
            Statement statement = conn.createStatement();
            String nameServiceCompany = serviceCompany.getName();
            String sql1 = "SELECT \"VinList\" FROM \"ServiceCompany\" WHERE \"ServiceCompany\" = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.setString(1, nameServiceCompany);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String resString = rs.getArray(1).toString();
                resString = resString.replace('{', ' ');
                resString = resString.replace('}', ' ');
                resString = resString.trim();
                String[] arrayList = resString.split(",");
                Collections.addAll(res, arrayList);
            }
        } catch (Exception ex) {
            System.out.println("ServiceCompanyRepository Exception::getAllAutoToServiceCompany");
        }
        return res;
    }

    @Override
    public List<String> getAllServiceCompany() {
        List<String> res = new ArrayList<>();
        try (Connection conn = dbConnectionProvider.getConnection()) {
            Statement statement = conn.createStatement();
            String sql = "SELECT \"ServiceCompany\" FROM \"ServiceCompany\"";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String resString = rs.getArray(1).toString();
                resString = resString.replace('{', ' ');
                resString = resString.replace('}', ' ');
                resString = resString.trim();
                String[] arrayList = resString.split(",");
                Collections.addAll(res, arrayList);
            }
        } catch (Exception ex) {
            System.out.println("ServiceCompanyRepository Exception::getAllServiceCompany");
        }
        return res;
    }

    public boolean deleteAllServiceCompany() {
        boolean res = false;

        try (Connection conn = dbConnectionProvider.getConnection()) {
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM \"ServiceCompany\"";
            res = true;
            ResultSet rs = statement.executeQuery(sql);
        } catch (Exception ex) {
            System.out.println("ServiceCompanyRepository Exception::deleteAllServiceCompany");
        }
        return res;
    }

    public ServiceCompanyEntity getServiceCompanyToName(String nameServiceCompany) {
        ServiceCompanyEntity result = null;
        for (int i = 0; i <= this.getAllServiceCompany().size() - 1; i++) {
            getAllServiceCompany().get(i).equals(nameServiceCompany);
            result = new ServiceCompanyEntity(nameServiceCompany);
        }
        return result;
    }

    public boolean addVinToServiceCompany(AutoEntity newAuto) {
        ServiceCompanyEntity cahsServiceCompany = new ServiceCompanyEntity(newAuto.getNameServiceCompany());
        ArrayList<String> cashVin = new ArrayList<>(this.getAllAutoToServiceCompany(cahsServiceCompany));
        this.delete(getServiceCompanyToName(newAuto.getNameServiceCompany()));
        ArrayList<String> newVinList = new ArrayList<>();
        if (cashVin.size() != 0) {
            if (cashVin.get(0).equals("")) {
                newVinList.add(newAuto.getVin());
            } else {
                for (int i = 0; i <= cashVin.size() - 1; i++) {
                    if (!cashVin.get(i).equals("")) {
                        newVinList.add(cashVin.get(i));
                    }
                }
                newVinList.add(newAuto.getVin());
            }
        } else {
            newVinList.add(newAuto.getVin());
        }
        cahsServiceCompany.setAllVin(newVinList);
        return this.create(cahsServiceCompany);
    }

    public boolean deleteVinToServiceCompany(AutoEntity newAuto) {
        boolean result = false;
        ServiceCompanyEntity cahsServiceCompany = new ServiceCompanyEntity(newAuto.getNameServiceCompany());
        ArrayList<String> cashVin = new ArrayList<>(this.getAllAutoToServiceCompany(cahsServiceCompany));
        this.delete(getServiceCompanyToName(newAuto.getNameServiceCompany()));
        ArrayList<String> newVinList = new ArrayList<>();
        for (int i = 0; i <= cashVin.size() - 1; i++) {
            if (cashVin.get(i).equals(newAuto.getVin())) {
                cashVin.remove(i);
                result = true;
            }
        }
        cahsServiceCompany.setAllVin(cashVin);
        this.create(cahsServiceCompany);
        return result;
    }

    public boolean updateServiceCompany(ServiceCompanyEntity oldCompany, ServiceCompanyEntity newCompany) {
        ArrayList<String> cashBaseVin = new ArrayList<>(this.getAllAutoToServiceCompany(new ServiceCompanyEntity(oldCompany.getName())));
        this.delete(oldCompany);
        newCompany.setAllVin(cashBaseVin);
        this.create(newCompany);
        return true;
    }
}
