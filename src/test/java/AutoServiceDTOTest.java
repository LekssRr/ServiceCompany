import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.sevice.AutoService;
import ru.warrantyauto.sevice.ServiceCompanySevice;

import java.util.ArrayList;

public class AutoServiceDTOTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");
    AutoService autoService;
    ServiceCompanySevice serviceCompanySevice;
    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }
    @AfterAll
    static void afterAll() {
        postgres.stop();
    }
    @BeforeEach
    void setUpAuto() {
        DBConnectionProvider connectionProvider = new DBConnectionProvider(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
        );
        autoService = new AutoService(connectionProvider);
    }
    @BeforeEach
    void setUpService() {
        DBConnectionProvider connectionProvider = new DBConnectionProvider(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
        );
        serviceCompanySevice = new ServiceCompanySevice(connectionProvider);
    }
    @Test
    public void deleteAutoTest()
    {

        new AutoDTO("22222222222222222", "SC-2", new ServiceCompanyDTO("SC-2"));
        autoService.addAuto("22222222222222222", "SC-2");
        Assertions.assertEquals(autoService.deleteAuto("22222222222222222"), true);

    }
    @Test
    public void addAutoTest()
    {

        serviceCompanySevice.addServiceCompany(new ServiceCompanyDTO("SC-1").getName());
        Assertions.assertEquals(autoService.addAuto("11111", "SC-1"), true);
        Assertions.assertEquals(autoService.addAuto("11121", "SC-1"), true);
        Assertions.assertEquals(autoService.addAuto("13111", "SC-1"), true);
        serviceCompanySevice.deleteServiceCompany(new ServiceCompanyDTO("SC-1").getName());
        autoService.deleteAuto("11121");
        autoService.deleteAuto("13111");

    }
    @Test
    public void updateAutoTest()
    {
        ServiceCompanyDTO testServiceCompany = new ServiceCompanyDTO("SC-1");
        serviceCompanySevice.addServiceCompany(testServiceCompany.getName());
        autoService.addAuto("22222", "SC-1");
        Assertions.assertEquals(autoService.updateAuto("22222", "SC-2"), true);
        autoService.deleteAuto("22222");
        serviceCompanySevice.deleteServiceCompany("SC-2");
        serviceCompanySevice.deleteServiceCompany("SC-1");

    }
    @Test
    void doesCarExistTest()
    {

        ServiceCompanyDTO testServiceCompany = new ServiceCompanyDTO("SC-222");
        serviceCompanySevice.addServiceCompany(testServiceCompany.getName());
        autoService.addAuto("33333333333333331", "SC-222");
        AutoDTO testAuto = new AutoDTO("33333333333333331", "SC-222", new ServiceCompanyDTO("SC-222"));
        Assertions.assertEquals(autoService.doesCarExist("33333333333333331"), true);
        autoService.deleteAuto("33333333333333331");
        serviceCompanySevice.deleteServiceCompany(testServiceCompany.getName());

    }
    @Test
    void getAllAutoTest()
    {


        ServiceCompanyDTO testServiceCompany = new ServiceCompanyDTO("SC-1");
        ServiceCompanyDTO testServiceCompany1 = new ServiceCompanyDTO("SC-2");
        ServiceCompanyDTO testServiceCompany2 = new ServiceCompanyDTO("SC-3");
        serviceCompanySevice.addServiceCompany(testServiceCompany.getName());
        serviceCompanySevice.addServiceCompany(testServiceCompany1.getName());
        serviceCompanySevice.addServiceCompany(testServiceCompany2.getName());
        autoService.addAuto("33333333333333331", "SC-1");
        autoService.addAuto("33333333334333331", "SC-2");
        ArrayList<String> test = new ArrayList<>();
        test.add("33333333333333331");
        test.add("33333333334333331");

        Assertions.assertEquals(autoService.getAllAuto(), test);
        serviceCompanySevice.deleteServiceCompany(testServiceCompany.getName());
        serviceCompanySevice.deleteServiceCompany(testServiceCompany1.getName());
        serviceCompanySevice.deleteServiceCompany(testServiceCompany2.getName());
        deleteAutoTest();
    }
    @Test
    void getServiceCompanyToVinTest()
    {

        ServiceCompanyDTO serviceCompany = new ServiceCompanyDTO("SC-22");
        serviceCompanySevice.addServiceCompany(serviceCompany.getName());
        autoService.addAuto("11111111111111115", "SC-22");
        Assertions.assertEquals(autoService.getServiceCompanyToVin("11111111111111115"), "SC-22");
        autoService.deleteAuto("11111111111111115");
        serviceCompanySevice.deleteServiceCompany(serviceCompany.getName());
    }
}
