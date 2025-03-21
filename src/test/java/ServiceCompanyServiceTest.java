import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.sevice.AutoService;
import ru.warrantyauto.sevice.ServiceCompanySevice;

import java.util.ArrayList;

public class ServiceCompanyServiceTest {
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
    void addServiceCompanyTest()
    {
        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-7");
        ServiceCompanyDTO sc1 = new ServiceCompanyDTO("SC-5");
        ServiceCompanyDTO sc2 = new ServiceCompanyDTO("SC-2");
        serviceCompanySevice.addServiceCompany(sc.getName());
        serviceCompanySevice.addServiceCompany(sc1.getName());
        serviceCompanySevice.addServiceCompany(sc2.getName());
        serviceCompanySevice.deleteServiceCompany("SC-1");
        Assertions.assertEquals(serviceCompanySevice.addServiceCompany("SC-7"), false);
        Assertions.assertEquals(serviceCompanySevice.addServiceCompany("SC-1"), true);
        serviceCompanySevice.deleteServiceCompany("SC-1");
        serviceCompanySevice.deleteServiceCompany("SC-7");
        serviceCompanySevice.deleteServiceCompany("SC-5");
        serviceCompanySevice.deleteServiceCompany("SC-2");
    }
    @Test
    void deleteServiceCompany()
    {

        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-7");
        ServiceCompanyDTO sc1 = new ServiceCompanyDTO("SC-5");
        ServiceCompanyDTO sc2 = new ServiceCompanyDTO("SC-2");
        serviceCompanySevice.addServiceCompany(sc.getName());
        serviceCompanySevice.addServiceCompany(sc1.getName());
        serviceCompanySevice.addServiceCompany(sc2.getName());
        serviceCompanySevice.deleteServiceCompany("SC-1");
        Assertions.assertEquals(serviceCompanySevice.deleteServiceCompany("SC-7"), true);
        Assertions.assertEquals(serviceCompanySevice.deleteServiceCompany("SC-1"), false);
        serviceCompanySevice.deleteServiceCompany("SC-2");
        serviceCompanySevice.deleteServiceCompany("SC-5");
    }
    @Test
    void updateServiceCompanyServiceTest()
    {
        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-99");
        ArrayList<String> test = new ArrayList<>();
        AutoDTO test1 = new AutoDTO("aaaaaa", "SC-99", new ServiceCompanyDTO("SC-99"));
        AutoDTO test2 = new AutoDTO("bbbbbb", "SC-99", new ServiceCompanyDTO("SC-99"));
        AutoDTO test3 = new AutoDTO("vvvvvv", "SC-99", new ServiceCompanyDTO("SC-99"));
        test.add("aaaaaa");
        test.add("bbbbbb");
        test.add("vvvvvv");
        sc.setAllVin(test);
        autoService.addAuto(test1.getVin(), test1.getNameServiceCompany());
        autoService.addAuto(test2.getVin(), test2.getNameServiceCompany());
        autoService.addAuto(test3.getVin(), test3.getNameServiceCompany());
        serviceCompanySevice.addServiceCompany(sc.getName());
        Assertions.assertEquals(serviceCompanySevice.updateServiceCompany("SC-99", "SC-8"), true);
        autoService.deleteAuto(test1.getVin());
        autoService.deleteAuto(test2.getVin());
        autoService.deleteAuto(test3.getVin());
        serviceCompanySevice.deleteServiceCompany("SC-99");
        serviceCompanySevice.deleteServiceCompany("SC-8");
    }
    @Test
    void doesCarToServiceCompanyServiceTest()
    {


        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-99");
        serviceCompanySevice.addServiceCompany(sc.getName());
        AutoDTO a = new AutoDTO("11111111111111111", sc.getName(), sc);
        AutoDTO b = new AutoDTO("11111111111111121", sc.getName(), sc);
        AutoDTO c = new AutoDTO("11111111111111131", sc.getName(), sc);
        autoService.addAuto(a.getVin(), a.getNameServiceCompany());
        autoService.addAuto(b.getVin(), b.getNameServiceCompany());
        autoService.addAuto(c.getVin(), c.getNameServiceCompany());
        Assertions.assertEquals(autoService.doesCarToServiceCompany(a.getVin(), a.getNameServiceCompany()), true);
    }
}
