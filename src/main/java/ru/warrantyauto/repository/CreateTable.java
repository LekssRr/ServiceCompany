package ru.warrantyauto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    DBConnectionProvider dbConnectionProvider;

    public CreateTable(DBConnectionProvider newDbConnectionProvider) {
        this.dbConnectionProvider = newDbConnectionProvider;
    }
    void createCustomersTableIfNotExistsServiceCompany() {
        try (Connection conn = this.dbConnectionProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS \"ServiceCompany\"(\n" +
                    "    \"ServiceCompany\" character varying NOT NULL primary key,\n" +
                    "    \"VinList\" text[]\n" +
                    ")");
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void createCustomersTableIfNotExistsAuto() {
        try (Connection conn = this.dbConnectionProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS public.\"Auto\"\n" +
                    "(\n" +
                    "    \"Vin\" character varying(17) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    \"NameServiceCompany\" character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    CONSTRAINT \"Auto_pkey\" PRIMARY KEY (\"Vin\")\n" +
                    ")");
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
