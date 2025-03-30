package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.warrantyauto.servlet.AutoServlet;
import ru.warrantyauto.sevice.AutoService;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AutoServletTest {
    @Mock
    AutoService autoService;
    AutoServlet servlet = new AutoServlet(autoService);


    @Test
    public void getInUrlTest()
    {
        final String path = "/Auto/GET/";
        String[] urlRequest = path.split("/");
        Assertions.assertEquals(servlet.getInUrl(urlRequest), true);
    }
    @Test
    public void postInUrlTest()
    {
        final String path = "/Auto/POST/";
        String[] urlRequest = path.split("/");
        Assertions.assertEquals(servlet.postInUrl(urlRequest), true);
    }
    @Test
    public void deleteInUrlTest()
    {
        final String path = "/Auto/DELETE/";
        String[] urlRequest = path.split("/");
        Assertions.assertEquals(servlet.deleteInUrl(urlRequest), true);
    }
    @Test
    public void putInUrlTest()
    {
        final String path = "/Auto/PUT/";
        String[] urlRequest = path.split("/");

        Assertions.assertEquals(servlet.putInUrl(urlRequest), true);
    }
    @Test
    public void postVinUrlTest()
    {
        final String path = "/Auto/POST/vin/";
        String[] urlRequest = path.split("/");

        Assertions.assertEquals(servlet.postVinUrl(urlRequest), true);
    }

}
