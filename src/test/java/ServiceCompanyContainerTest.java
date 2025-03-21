import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
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
        Assertions.assertEquals(serviceCompanyRepository.create(new ServiceCompanyDTO("SC-00")), true);
    }
    @Test
    void getAllServiceCompanyTest()
    {
        serviceCompanyRepository.create(new ServiceCompanyDTO("SC-001"));
        serviceCompanyRepository.create(new ServiceCompanyDTO("SC-002"));
        serviceCompanyRepository.create(new ServiceCompanyDTO("SC-003"));
        List<String> AllServiceCompany = new ArrayList<>();
        AllServiceCompany.add("SC-001");
        AllServiceCompany.add("SC-002");
        AllServiceCompany.add("SC-003");
        Assertions.assertEquals(serviceCompanyRepository.getAllServiceCompany(), AllServiceCompany);
    }
    @Test
    void deleteVinToServiceCompany()
    {
        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-7");
        ArrayList<String> vinListSC = new ArrayList<>();
        vinListSC.add("1111111");
        vinListSC.add("12312312");
        vinListSC.add("2222222");
        sc.setAllVin(vinListSC);
        serviceCompanyRepository.create(sc);
        Assertions.assertEquals(serviceCompanyRepository.deleteVinToServiceCompany(new AutoDTO("2222222", "SC-7", new ServiceCompanyDTO("SC-7"))), true);
    }
    @Test
    void getVinToServiceCompany()
    {
        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-999");
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
        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-7");
        serviceCompanyRepository.deleteAllServiceCompany();
        ArrayList<String> vinListSC = new ArrayList<>();
        sc.setAllVin(vinListSC);
        serviceCompanyRepository.create(sc);
        AutoDTO one = new AutoDTO("7777777", "SC-7", new ServiceCompanyDTO("SC-7"));
        AutoDTO one1 = new AutoDTO("7771777", "SC-7", new ServiceCompanyDTO("SC-7"));
        AutoDTO one2 = new AutoDTO("2222", "SC-6", new ServiceCompanyDTO("SC-6"));
        AutoDTO one3 = new AutoDTO("22222", "SC-6", new ServiceCompanyDTO("SC-6"));
        AutoDTO one4 = new AutoDTO("333333", "SC-5", new ServiceCompanyDTO("SC-5"));
        AutoDTO one5 = new AutoDTO("777133333777", "SC-5", new ServiceCompanyDTO("SC-5"));
        AutoDTO one6 = new AutoDTO("777744444777", "SC-1", new ServiceCompanyDTO("SC-1"));
        AutoDTO one7 = new AutoDTO("77744441777", "SC-1", new ServiceCompanyDTO("SC-1"));
        AutoDTO one8 = new AutoDTO("77744441772227", "SC-999", new ServiceCompanyDTO("SC-999"));
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoDTO("7777777", "SC-7", new ServiceCompanyDTO("SC-7"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoDTO("7771777", "SC-7", new ServiceCompanyDTO("SC-7"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoDTO("2222", "SC-6", new ServiceCompanyDTO("SC-6"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoDTO("22222", "SC-6", new ServiceCompanyDTO("SC-6"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoDTO("333333", "SC-5", new ServiceCompanyDTO("SC-5"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoDTO("777133333777", "SC-5", new ServiceCompanyDTO("SC-5"))), true);
        Assertions.assertEquals(serviceCompanyRepository.addVinToServiceCompany(new AutoDTO("77744441772227", "SC-999", new ServiceCompanyDTO("SC-999"))), true);
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
        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-7");
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
        ServiceCompanyDTO sc = new ServiceCompanyDTO("SC-7");
        ServiceCompanyDTO sc1 = new ServiceCompanyDTO("SC-5");
        ServiceCompanyDTO sc2 = new ServiceCompanyDTO("SC-2");
        serviceCompanyRepository.create(sc);
        serviceCompanyRepository.create(sc1);
        serviceCompanyRepository.create(sc2);
        Assertions.assertEquals(serviceCompanyRepository.deleteAllServiceCompany(), true);
    }
    @Test
    void updateServicCompanyTest()
    {
        ServiceCompanyDTO serviceCompanyOld = new ServiceCompanyDTO("SC-22");
        ServiceCompanyDTO serviceCompanyNew = new ServiceCompanyDTO("SC-33");
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
