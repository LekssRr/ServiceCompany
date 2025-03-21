package ru.warrantyauto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.sevice.AutoService;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/Auto/*"})
public class AutoServlet extends HttpServlet {

    String url = "jdbc:postgresql://localhost:5432/auto_dealer";
    String user = "postgres";
    String password = "2112";
    private AutoDTO auto;
    private AutoService autoService = new AutoService(new DBConnectionProvider(url, user, password));
    @Override
    public void init() {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String url = request.getRequestURI();
            Map<String, String[]> param = request.getParameterMap();
            String[] urlRequest = url.split("/");
            if (urlRequest[2].equals("PUT"))
            {
                this.doPost(request,response);
            }else if(urlRequest[2].equals("POST"))
            {
                this.doPost(request,response);
            } else if (urlRequest[2].equals("DELETE")) {
                this.doPost(request,response);
            } else if(urlRequest[2].equals("GET"))
            {
                if(urlRequest.length == 3)
                {
                    response.getWriter().write(autoService.getAllAuto().toString());
                }
                if(urlRequest[3].equals("vin"))
                {
                    response.getWriter().write(autoService.getServiceCompanyToVin(urlRequest[4]));
                }
            }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String url = request.getRequestURI();
        String[] urlRequest = url.split("/");
    if(urlRequest[2].equals("POST"))
    {
        if (urlRequest[3].equals("vin"))
        {
            if (urlRequest[4].length() == 17)
            {

            if (!autoService.doesCarExist(urlRequest[4]))
                {
                    if (!autoService.doesCarToServiceCompany(urlRequest[4], urlRequest[5]))
                    {
                        response.getWriter().write("The vehicle has been successfully added to the database." + urlRequest[5] + "\n");
                        autoService.addAuto(urlRequest[4], urlRequest[5]);
                    }
                    else
                    {
                        response.getWriter().write("This service company does not exist, the car has not been added." + "\n");
                    }

                }
                else
                {
                    response.getWriter().write("This VIN is already in the car database" + "\n");
                }
            }
        }
        else
        {
            response.getWriter().write("Error" + "\n");
        }
    }
    else if (urlRequest[2].equals("DELETE"))
    {
        if(urlRequest.length >=4)
        {
            response.getWriter().print(autoService.deleteAuto(urlRequest[3]));
        }
        else
        {

        }
    }else if (urlRequest[2].equals("PUT"))
    {
        response.getWriter().print(autoService.updateAuto(urlRequest[3], urlRequest[4]));
    }
    }
}
