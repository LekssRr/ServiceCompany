package Servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.warrantyauto.servlet.ServiceCompanyServlet;
import ru.warrantyauto.sevice.ServiceCompanySevice;

import static org.mockito.Mockito.mock;
@ExtendWith(MockitoExtension.class)
public class ServiceCompanySrvletTest {

    ServiceCompanySevice serviceCompanySevice = Mockito.mock(ServiceCompanySevice.class);

    ServiceCompanyServlet serviceCompanyServlet = new ServiceCompanyServlet(serviceCompanySevice);
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
        Assertions.assertEquals(serviceCompanyServlet.getInUrl(urlRequest), true);
    }
    @Test
    void postInUrlTest()
    {
        String path = "/ServiceCompany/POST/";
        String[] urlRequest = path.split("/");
        Assertions.assertEquals(serviceCompanyServlet.postInUrl(urlRequest), true);
    }
    @Test
    void deleteInUrlTest()
    {
        String path = "/ServiceCompany/DELETE/";
        String[] urlRequest = path.split("/");
        Assertions.assertEquals(serviceCompanyServlet.deleteInUrl(urlRequest), true);
    }
    @Test
    void putInUrlTest()
    {
        String path = "/ServiceCompany/PUT/";
        String[] urlRequest = path.split("/");
        Assertions.assertEquals(serviceCompanyServlet.putInUrl(urlRequest), true);
    }
}
