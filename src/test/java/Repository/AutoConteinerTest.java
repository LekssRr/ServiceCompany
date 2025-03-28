package Repository;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.config.DBConnectionProvider;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;
import java.util.List;

public class AutoConteinerTest {

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
    void getAutoTest()
    {
        serviceCompanyRepository.create(new ServiceCompanyEntity("SC-5"));
        AutoEntity a = new AutoEntity("11111111111111115", "SC-5", new ServiceCompanyEntity("SC-5"));
        autoRepository.create(a);
        Assertions.assertEquals(autoRepository.get("11111111111111115"), "SC-5");
        autoRepository.delete(a);
        autoRepository.deleteAll();
        serviceCompanyRepository.deleteAllServiceCompany();
    }
    @Test
    void createTest()
    {
        autoRepository.delete(new AutoEntity("22222222222222222", "SC-2", new ServiceCompanyEntity("SC-2")));
        Assertions.assertEquals(autoRepository.create(new AutoEntity("22222222222222222", "SC-2", new ServiceCompanyEntity("SC-2"))), true);
        autoRepository.delete(new AutoEntity("22222222222222222", "SC-2", new ServiceCompanyEntity("SC-2")));
    }
    @Test
    void getAllServiceCompanyTest()
    {
        ServiceCompanyEntity serviceCompany = new ServiceCompanyEntity("SC-0001");
        ServiceCompanyEntity serviceCompany1 = new ServiceCompanyEntity("SC-0002");
        ServiceCompanyEntity serviceCompany2 = new ServiceCompanyEntity("SC-0003");
        AutoEntity a = new AutoEntity("88888888888888888", serviceCompany.getName(), serviceCompany);
        AutoEntity b = new AutoEntity("88888888888888881", serviceCompany1.getName(), serviceCompany1);
        AutoEntity c = new AutoEntity("88888888888888882", serviceCompany2.getName(), serviceCompany2);
        ArrayList<String> testvinList = new ArrayList<>();
        testvinList.add(serviceCompany.getName());
        testvinList.add(serviceCompany1.getName());
        testvinList.add(serviceCompany2.getName());
        serviceCompany.setAllVin(testvinList);
        serviceCompanyRepository.create(serviceCompany);
        autoRepository.create(a);
        autoRepository.create(b);
        autoRepository.create(c);
        Assertions.assertEquals(autoRepository.getAllAutoServiceCompany(), testvinList);
        autoRepository.deleteAll();
        serviceCompanyRepository.delete(serviceCompany);
   }
    @Test
    void getAllVinTest()
    {
        List<String> current = new ArrayList<String>();
        AutoEntity a = new AutoEntity("22222222222222221", "SC-1", new ServiceCompanyEntity("SC-1"));
        AutoEntity b = new AutoEntity("22222222222222223", "SC-2", new ServiceCompanyEntity("SC-2"));
        AutoEntity c = new AutoEntity("22222222222222224", "SC-3", new ServiceCompanyEntity("SC-3"));
        autoRepository.create(a);
        autoRepository.create(b);
        autoRepository.create(c);
        current.add("22222222222222221");
        current.add("22222222222222223");
        current.add("22222222222222224");
        Assertions.assertEquals(autoRepository.getAllAutoVin(), current);
        autoRepository.deleteAll();
    }
    @Test
    void deleteAllTest()
    {
        AutoEntity a = new AutoEntity("22222222222222221", "SC-1", new ServiceCompanyEntity("SC-2"));
        AutoEntity b = new AutoEntity("22222222222222223", "SC-2", new ServiceCompanyEntity("SC-2"));
        AutoEntity c = new AutoEntity("22222222222222224", "SC-3", new ServiceCompanyEntity("SC-2"));
        autoRepository.create(a);
        autoRepository.create(b);
        autoRepository.create(c);
        Assertions.assertEquals(autoRepository.deleteAll(), true);

    }
    @Test
    void deleteTest()
    {
        AutoEntity test = new AutoEntity("11111111111111115", "SC-5", new ServiceCompanyEntity("SC-5"));
        autoRepository.create(test);
        Assertions.assertEquals(autoRepository.delete(test), true);
        autoRepository.deleteAll();
    }
    @Test
    void updatetest()
    {
        AutoEntity test = new AutoEntity("11111111111111115", "SC-5", new ServiceCompanyEntity("SC-5"));
        autoRepository.create(test);
        AutoEntity test1 = new AutoEntity("11111111111111115", "SC-6", new ServiceCompanyEntity("SC-6"));
        Assertions.assertEquals(autoRepository.update(test1), true);
        autoRepository.delete(test1);
    }
    @Test
    void doesCarToServiceCompanyRepositoryTest()
    {
        ServiceCompanyEntity testServiceCompany = new ServiceCompanyEntity("SC-222");


        AutoEntity testAuto = new AutoEntity("77777777777777777", testServiceCompany.getName(), testServiceCompany);
        AutoEntity testAuto1 = new AutoEntity("77777777777777778", testServiceCompany.getName(), testServiceCompany);
        AutoEntity testAuto2 = new AutoEntity("77777777777777787", testServiceCompany.getName(), testServiceCompany);
        ArrayList<String> test = new ArrayList<>();
        test.add(testAuto.getVin());
        test.add(testAuto1.getVin());
        test.add(testAuto2.getVin());
        testServiceCompany.setAllVin(test);
        serviceCompanyRepository.create(testServiceCompany);
        autoRepository.create(testAuto);
        autoRepository.create(testAuto1);
        autoRepository.create(testAuto2);

        Assertions.assertEquals(autoRepository.doesCarToServiceCompanyRepository(testAuto), true);

    }
}
