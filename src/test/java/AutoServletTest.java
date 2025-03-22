import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.servlet.AutoServlet;
import ru.warrantyauto.sevice.AutoService;
import ru.warrantyauto.sevice.ServiceCompanySevice;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class AutoServletTest {

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getAllAutoTestServlet() throws ServletException, IOException {
        final String path = "/Auto/GET/";
        String[] urlRequest = path.split("/");
        final AutoServlet servlet = new AutoServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter printStream = response.getWriter();
        Mockito.when(response.getWriter()).thenReturn(printStream);
        when(request.getRequestURI()).thenReturn(path);
    }
    @Test
    public void getInUrlTest()
    {
        final String path = "/Auto/GET/";
        String[] urlRequest = path.split("/");
        final AutoServlet servlet = new AutoServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        Assertions.assertEquals(servlet.getInUrl(urlRequest), true);
    }
    @Test
    public void postInUrlTest()
    {
        final String path = "/Auto/POST/";
        String[] urlRequest = path.split("/");
        final AutoServlet servlet = new AutoServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        Assertions.assertEquals(servlet.postInUrl(urlRequest), true);
    }
    @Test
    public void deleteInUrlTest()
    {
        final String path = "/Auto/DELETE/";
        String[] urlRequest = path.split("/");
        final AutoServlet servlet = new AutoServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        Assertions.assertEquals(servlet.deleteInUrl(urlRequest), true);
    }
    @Test
    public void putInUrlTest()
    {
        final String path = "/Auto/PUT/";
        String[] urlRequest = path.split("/");
        final AutoServlet servlet = new AutoServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        Assertions.assertEquals(servlet.putInUrl(urlRequest), true);
    }
    @Test
    public void postVinUrlTest()
    {
        final String path = "/Auto/POST/vin/";
        String[] urlRequest = path.split("/");
        final AutoServlet servlet = new AutoServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        Assertions.assertEquals(servlet.postVinUrl(urlRequest), true);
    }
    @Test
    public void doesCarExistServletTest()
    {
        final String path = "/Auto/POST/vin/11111111111111111/SC-1";
        String[] urlRequest = path.split("/");
        final AutoServlet servlet = new AutoServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        Assertions.assertEquals(servlet.doesCarExistServlet(urlRequest), false);
    }
    @Test
    public void doesCarToServiceCompanyServletTest()
    {
        final String path = "/Auto/POST/vin/11111111111111111/SC-1";
        String[] urlRequest = path.split("/");
        final AutoServlet servlet = new AutoServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        Assertions.assertEquals(servlet.doesCarExistServlet(urlRequest), false);
    }
}
