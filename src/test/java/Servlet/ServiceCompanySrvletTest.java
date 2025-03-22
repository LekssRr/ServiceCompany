package Servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import ru.warrantyauto.servlet.ServiceCompanyServlet;

import static org.mockito.Mockito.mock;

public class ServiceCompanySrvletTest {

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInUrlTest()
    {
        String path = "/ServiceCompany/GET/";
        String[] urlRequest = path.split("/");
        final ServiceCompanyServlet serviceCompanyServlet = new ServiceCompanyServlet();
        Assertions.assertEquals(serviceCompanyServlet.getInUrl(urlRequest), true);
    }
    @Test
    void postInUrlTest()
    {
        String path = "/ServiceCompany/POST/";
        String[] urlRequest = path.split("/");
        final ServiceCompanyServlet serviceCompanyServlet = new ServiceCompanyServlet();
        Assertions.assertEquals(serviceCompanyServlet.postInUrl(urlRequest), true);
    }
    @Test
    void deleteInUrlTest()
    {
        String path = "/ServiceCompany/DELETE/";
        String[] urlRequest = path.split("/");
        final ServiceCompanyServlet serviceCompanyServlet = new ServiceCompanyServlet();
        Assertions.assertEquals(serviceCompanyServlet.deleteInUrl(urlRequest), true);
    }
    @Test
    void putInUrlTest()
    {
        String path = "/ServiceCompany/PUT/";
        String[] urlRequest = path.split("/");
        final ServiceCompanyServlet serviceCompanyServlet = new ServiceCompanyServlet();
        Assertions.assertEquals(serviceCompanyServlet.putInUrl(urlRequest), true);
    }
}
