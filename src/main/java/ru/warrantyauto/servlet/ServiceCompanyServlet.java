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

        if(this.postInUrl(urlRequest))
        {
            doPost(request, response);
        }
        else if (this.getInUrl(urlRequest))
        {
            if(urlRequest.length == 3)
            {
                    response.getWriter().write(serviceCompanyService.getAllServiceCompany().toString());
            }
            else if(urlRequest.length >=4)
            {
                response.getWriter().write(serviceCompanyService.getAllVinServiceCompany(urlRequest[3]));
            }
        } else if (this.deleteInUrl(urlRequest)) {
            doPost(request, response);
        } else if (this.putInUrl(urlRequest)) {
            doPost(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String[] urlRequest = url.split("/");

        if(this.postInUrl(urlRequest))
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
        } else if (deleteInUrl(urlRequest)) {

            if(!serviceCompanyService.addServiceCompany(urlRequest[3]))
            {
                response.getWriter().write("Delete SC" + "\n");
                serviceCompanyService.deleteServiceCompany(urlRequest[3]);
            }
            else
            {
                response.getWriter().write("NotSK" + "\n");
            }
        }else if(this.putInUrl(urlRequest))
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
    public boolean getInUrl(String[] urlRequest)
    {
        return urlRequest[2].equals("GET");
    }
    public boolean postInUrl(String[] urlRequest)
    {
        return urlRequest[2].equals("POST");
    }
    public boolean deleteInUrl(String[] urlRequest)
    {
        return urlRequest[2].equals("DELETE");
    }
    public boolean putInUrl(String[] urlRequest)
    {
        return urlRequest[2].equals("PUT");
    }
}

