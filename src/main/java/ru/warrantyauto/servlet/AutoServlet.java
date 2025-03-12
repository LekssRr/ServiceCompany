package ru.warrantyauto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.model.Model;
import ru.warrantyauto.sevice.AutoService;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/Auto/*"})
public class AutoServlet extends HttpServlet {

    private Auto auto;
    private AutoService autoService = new AutoService();
    @Override
    public void init() {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String url = request.getRequestURI();
            Map<String, String[]> param = request.getParameterMap();
            String[] urlRequest = url.split("/");

            if(urlRequest[2].equals("POST"))
            {
                this.doPost(request,response);
            } else if (urlRequest[2].equals("DELETE")) {

            } else if(urlRequest[2].equals("GET"))
            {
                if(urlRequest[3].equals("vin"))
                {
                    response.getWriter().write("get vin");
                }
                else if (urlRequest[3].equals("sk"))
                {
                    response.getWriter().write("get SK");
                }
                else if(urlRequest[3].equals("allCar"))
                {
                    for (int i = 0; i<=Model.getInstance().getAuto().size()-1; i++)
                    {
                        response.getWriter().write(Model.getInstance().getAuto().get(i).getName() + "\n");
                    }
                }
            }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String url = request.getRequestURI();
        String[] urlRequest = url.split("/");

        if (urlRequest[3].equals("vin"))
        {
            if (urlRequest[4].length() == 17)
            {

                if (autoService.doesCarExist(urlRequest[4]))
                {
                    if (autoService.doesCarToServiceCompany(urlRequest[5]))
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
}
