package Repository;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.List;

public class ServiceCompanyContainerTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");
    AutoRepository autoRepository;
    ServiceCompanyRepository serviceCompanyRepository;
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
        autoRepository = new AutoRepository(connectionProvider);
    }
    @BeforeEach
    void setUpService() {
        DBConnectionProvider connectionProvider = new DBConnectionProvider(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
        );
        serviceCompanyRepository = new ServiceCompanyRepository(connectionProvider);
    }
    @Test
    void createServiceCompanyTest()
    {
        Assertions.assertEquals(serviceCompanyRepository.create(new ServiceCompanyEntity("SC-00")), true);
    }
    @Test
    void getAllServiceCompanyTest()
    {
        serviceCompanyRepository.create(new ServiceCompanyEntity("SC-001"));
        serviceCompanyRepository.create(new ServiceCompanyEntity("SC-002"));
        serviceCompanyRepository.create(new ServiceCompanyEntity("SC-003"));
        List<String> AllServiceCompany = new ArrayList<>();
        AllServiceCompany.add("SC-001");
        AllServiceCompany.add("SC-002");
        AllServiceCompany.add("SC-003");
        Assertions.assertEquals(serviceCompanyRepository.getAllServiceCompany(), AllServiceCompany);
    }
    @Test
    void deleteVinToServiceCompany()
    {
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-7");
        ArrayList<String> vinListSC = new ArrayList<>();
        vinListSC.add("1111111");
        vinListSC.add("12312312");
        vinListSC.add("2222222");
        sc.setAllVin(vinListSC);
        serviceCompanyRepository.create(sc);
        Assertions.assertEquals(serviceCompanyRepository.deleteVinToServiceCompany(new AutoEntity("2222222", "SC-7", new ServiceCompanyEntity("SC-7"))), true);
    }
    @Test
    void getVinToServiceCompany()
    {
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-999");
        ArrayList<String> test = new ArrayList<>();
        test.add("fsdfsdfs");
        test.add("sdadasdada");
        sc.setAllVin(test);
        serviceCompanyRepository.create(sc);
        Assertions.assertEquals(serviceCompanyRepository.getAllAutoToServiceCompany(sc), test);
        serviceCompanyRepository.delete(sc);
    }
    @Test
    void addVinToServiceCompanyTest()
    {
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-7");
        serviceCompanyRepository.deleteAllServiceCompany();
        ArrayList<String> vinListSC = new ArrayList<>();
        sc.setAllVin(vinListSC);
        serviceCompanyRepository.create(sc);
        AutoEntity one = new AutoEntity("7777777", "SC-7", new ServiceCompanyEntity("SC-7"));
        AutoEntity one1 = new AutoEntity("7771777", "SC-7", new ServiceCompanyEntity("SC-7"));
        AutoEntity one2 = new AutoEntity("2222", "SC-6", new ServiceCompanyEntity("SC-6"));
        AutoEntity one3 = new AutoEntity("22222", "SC-6", new ServiceCompanyEntity("SC-6"));
        AutoEntity one4 = new AutoEntity("333333", "SC-5", new ServiceCompanyEntity("SC-5"));
        AutoEntity one5 = new AutoEntity("777133333777", "SC-5", new ServiceCompanyEntity("SC-5"));
        AutoEntity one6 = new AutoEntity("777744444777", "SC-1", new ServiceCompanyEntity("SC-1"));
        AutoEntity one7 = new AutoEntity("77744441777", "SC-1", new ServiceCompanyEntity("SC-1"));
        AutoEntity one8 = new AutoEntity("77744441772227", "SC-999", new ServiceCompanyEntity("SC-999"));
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("7777777", "SC-7", new ServiceCompanyEntity("SC-7"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("7771777", "SC-7", new ServiceCompanyEntity("SC-7"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("2222", "SC-6", new ServiceCompanyEntity("SC-6"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("22222", "SC-6", new ServiceCompanyEntity("SC-6"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("333333", "SC-5", new ServiceCompanyEntity("SC-5"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("777133333777", "SC-5", new ServiceCompanyEntity("SC-5"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("77744441772227", "SC-999", new ServiceCompanyEntity("SC-999"))), true);
        serviceCompanyRepository.deleteAllServiceCompany();
        autoRepository.delete(one);
        autoRepository.delete(one1);
        autoRepository.delete(one2);
        autoRepository.delete(one3);
        autoRepository.delete(one4);
        autoRepository.delete(one5);
        autoRepository.delete(one6);
        autoRepository.delete(one7);
        autoRepository.delete(one8);
    }
    @Test
    void updateTest()
    {
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-7");
        ArrayList<String> test = new ArrayList<>();
        test.add("12345");
        test.add("54673");
        test.add("232153");
        sc.setAllVin(test);
        serviceCompanyRepository.update(sc);
        Assertions.assertEquals(serviceCompanyRepository.update(sc), true);
        serviceCompanyRepository.delete(sc);
    }
    @Test
    void deleteAllServiceCompany()
    {
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-7");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-5");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-2");
        serviceCompanyRepository.create(sc);
        serviceCompanyRepository.create(sc1);
        serviceCompanyRepository.create(sc2);
        Assertions.assertEquals(serviceCompanyRepository.deleteAllServiceCompany(), true);
    }
    @Test
    void updateServicCompanyTest()
    {
        ServiceCompanyEntity serviceCompanyOld = new ServiceCompanyEntity("SC-22");
        ServiceCompanyEntity serviceCompanyNew = new ServiceCompanyEntity("SC-33");
        ArrayList<String> vinList= new ArrayList<>();
        vinList.add("11111");
        vinList.add("22222");
        vinList.add("33333");
        serviceCompanyOld.setAllVin(vinList);
        serviceCompanyRepository.create(serviceCompanyOld);
        Assertions.assertEquals(serviceCompanyRepository.updateServiceCompany(serviceCompanyOld, serviceCompanyNew), true);
        serviceCompanyRepository.deleteAllServiceCompany();
    }
}
