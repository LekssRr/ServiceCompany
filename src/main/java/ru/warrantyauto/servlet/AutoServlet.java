package ru.warrantyauto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ru.warrantyauto.config.DBConnectionProvider;
import ru.warrantyauto.sevice.AutoService;

import java.io.*;
import java.util.*;

@WebServlet(urlPatterns = {"/Auto/*"})
public class AutoServlet extends HttpServlet {
    DBConnectionProvider dbConnectionProvider = new DBConnectionProvider();
    private final AutoService autoService;

    public AutoServlet() {
        this.autoService = new AutoService(dbConnectionProvider);
    }

    @Override
    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getRequestURI();
        Map<String, String[]> param = request.getParameterMap();
        String[] urlRequest = url.split("/");
        if (this.putInUrl(urlRequest)) {
            this.doPost(request, response);
        } else if (this.postInUrl(urlRequest)) {
            this.doPost(request, response);
        } else if (this.deleteInUrl(urlRequest)) {
            this.doPost(request, response);
        } else if (urlRequest[2].equals("GET")) {
            if (urlRequest.length == 3) {
                response.getWriter().write(autoService.getAllAuto().toString());

            }
            if (urlRequest[3].equals("vin")) {
                response.getWriter().write(autoService.getServiceCompanyToVin(urlRequest[4]).getName());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String url = request.getRequestURI();
        String[] urlRequest = url.split("/");

        if (this.postInUrl(urlRequest)) {
            if (this.postVinUrl(urlRequest)) {
                if (urlRequest[4].length() == 17) {
                    if (!this.doesCarExistServlet(urlRequest)) {
                        if (!this.doesCarToServiceCompanyServlet(urlRequest)) {
                            response.getWriter().write("The vehicle has been successfully added to the database." + urlRequest[5] + "\n");
                            autoService.addAuto(urlRequest[4], urlRequest[5]);
                        } else {
                            response.getWriter().write("This service company does not exist, the car has not been added." + "\n");
                        }

                    } else {
                        response.getWriter().write("This VIN is already in the car database" + "\n");
                    }
                }
            } else {
                response.getWriter().write("Error" + "\n");
            }
        } else if (this.deleteInUrl(urlRequest)) {
            if (urlRequest.length >= 4) {
                response.getWriter().print(autoService.deleteAuto(urlRequest[3]));
            } else {

            }
        } else if (this.putInUrl(urlRequest)) {
            response.getWriter().print(autoService.updateAuto(urlRequest[3], urlRequest[4]));
        }
    }

    public boolean getInUrl(String[] urlRequest) {
        return urlRequest[2].equals("GET");
    }

    public boolean postInUrl(String[] urlRequest) {
        return urlRequest[2].equals("POST");
    }

    public boolean deleteInUrl(String[] urlRequest) {
        return urlRequest[2].equals("DELETE");
    }

    public boolean putInUrl(String[] urlRequest) {
        return urlRequest[2].equals("PUT");
    }

    public boolean postVinUrl(String[] urlRequest) {
        return urlRequest[3].equals("vin");
    }

    public boolean doesCarExistServlet(String[] urlRequest) {
        return autoService.doesCarExist(urlRequest[4]);
    }

    private boolean doesCarToServiceCompanyServlet(String[] urlRequest) {
        return autoService.doesCarToServiceCompany(urlRequest[4], urlRequest[5]);
    }
}
