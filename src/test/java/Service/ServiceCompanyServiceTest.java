package Service;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;
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
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-7");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-5");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-2");
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

        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-7");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-5");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-2");
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
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-99");
        ArrayList<String> test = new ArrayList<>();
        AutoEntity test1 = new AutoEntity("aaaaaa", "SC-99", new ServiceCompanyEntity("SC-99"));
        AutoEntity test2 = new AutoEntity("bbbbbb", "SC-99", new ServiceCompanyEntity("SC-99"));
        AutoEntity test3 = new AutoEntity("vvvvvv", "SC-99", new ServiceCompanyEntity("SC-99"));
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


        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-99");
        serviceCompanySevice.addServiceCompany(sc.getName());
        AutoEntity a = new AutoEntity("11111111111111111", sc.getName(), sc);
        AutoEntity b = new AutoEntity("11111111111111121", sc.getName(), sc);
        AutoEntity c = new AutoEntity("11111111111111131", sc.getName(), sc);
        autoService.addAuto(a.getVin(), a.getNameServiceCompany());
        autoService.addAuto(b.getVin(), b.getNameServiceCompany());
        autoService.addAuto(c.getVin(), c.getNameServiceCompany());
        Assertions.assertEquals(autoService.doesCarToServiceCompany(a.getVin(), a.getNameServiceCompany()), true);
    }
    @Test
    void getAllServiceCompanyTest()
    {
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-91");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-92");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-93");
        serviceCompanySevice.addServiceCompany(sc.getName());
        serviceCompanySevice.addServiceCompany(sc1.getName());
        serviceCompanySevice.addServiceCompany(sc2.getName());
        ArrayList<ServiceCompanyDTO> serviceCompanyDTOs = new ArrayList<>();
        ServiceCompanyDTO scd = new ServiceCompanyDTO("SC-91");
        ServiceCompanyDTO scd1 = new ServiceCompanyDTO("SC-92");
        ServiceCompanyDTO scd2 = new ServiceCompanyDTO("SC-93");
        serviceCompanyDTOs.add(scd);
        serviceCompanyDTOs.add(scd1);
        serviceCompanyDTOs.add(scd2);
        Assertions.assertEquals(serviceCompanySevice.getAllServiceCompany().size()-1, serviceCompanyDTOs.size());
    }
}
