package ru.warrantyauto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.sevice.ServiceCompanySevice;
import java.io.IOException;

@WebServlet(urlPatterns = {"/ServiceCompany/*"})
public class ServiceCompanyServlet extends HttpServlet {
    String url = "jdbc:postgresql://localhost:5432/auto_dealer";
    String user = "postgres";
    String password = "2112";

    private final ServiceCompanySevice serviceCompanyService = new ServiceCompanySevice(new DBConnectionProvider(url, user,password));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getRequestURI();
        String[] urlRequest = url.split("/");

        if(urlRequest[2].equals("POST"))
        {
            doPost(request, response);
        }
        else if (urlRequest[2].equals("GET"))
        {
            if(urlRequest.length == 3)
            {
                //response.getWriter().write(DataBase.getPostgresConnection().getCatalog());
                response.getWriter().write(serviceCompanyService.getAllServiceCompany().toString());
            }
            else if(urlRequest.length >=4)
            {
                response.getWriter().write(serviceCompanyService.getAllVinServiceCompany(urlRequest[3]));
            }
        } else if (urlRequest[2].equals("DELETE")) {
            doPost(request, response);
        } else if (urlRequest[2].equals("PUT")) {
            doPost(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String[] urlRequest = url.split("/");

        if(urlRequest[2].equals("POST"))
        {
            if(serviceCompanyService.addServiceCompany(urlRequest[3]))
            {
                response.getWriter().write("AddSk" + "\n");
                serviceCompanyService.addServiceCompany(urlRequest[3]);
            }
            else
            {
                response.getWriter().write("NotAddSK" + "\n");
            }
        } else if (urlRequest[2].equals("DELETE")) {

            if(!serviceCompanyService.addServiceCompany(urlRequest[3]))
            {
                response.getWriter().write("Delete SC" + "\n");
                serviceCompanyService.deleteServiceCompany(urlRequest[3]);
            }
            else
            {
                response.getWriter().write("NotSK" + "\n");
            }
        }else if(urlRequest[2].equals("PUT"))
        {
            if(!serviceCompanyService.addServiceCompany(urlRequest[3]))
            {
               if (serviceCompanyService.updateServiceCompany(urlRequest[3], urlRequest[4]))
                {
                    response.getWriter().write("true");
                }
               else
               {
                   response.getWriter().write("false");
               }
            }

        }
    }

}

