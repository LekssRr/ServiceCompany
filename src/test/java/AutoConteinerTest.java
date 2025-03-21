import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.DBConnectionProvider;
import ru.warrantyauto.repository.ServiceCompanyRepository;
import ru.warrantyauto.sevice.AutoService;

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
        serviceCompanyRepository.create(new ServiceCompany("SC-5"));
        Auto a = new Auto("11111111111111115", "SC-5", new ServiceCompany("SC-5"));
        autoRepository.create(a);
        Assertions.assertEquals(autoRepository.get("11111111111111115"), "SC-5");
        autoRepository.delete(a);
        autoRepository.deleteAll();
        serviceCompanyRepository.deleteAllServiceCompany();
    }
    @Test
    void createTest()
    {
        autoRepository.delete(new Auto("22222222222222222", "SC-2", new ServiceCompany("SC-2")));
        Assertions.assertEquals(autoRepository.create(new Auto("22222222222222222", "SC-2", new ServiceCompany("SC-2"))), true);
    }
    @Test
    void getAllServiceCompanyTest()
    {
        List<String> current = new ArrayList<String>();
        serviceCompanyRepository.create(new ServiceCompany("SC-1"));
        serviceCompanyRepository.create(new ServiceCompany("SC-2"));
        serviceCompanyRepository.create(new ServiceCompany("SC-3"));
        Auto a = new Auto("22222222222222221", "SC-1", new ServiceCompany("SC-1"));
        Auto b = new Auto("22222222222222223", "SC-2", new ServiceCompany("SC-2"));
        Auto c = new Auto("22222222222222224", "SC-3", new ServiceCompany("SC-3"));
        autoRepository.create(a);
        autoRepository.create(b);
        autoRepository.create(c);
        current.add("SC-1");
        current.add("SC-2");
        current.add("SC-3");
        Assertions.assertEquals(autoRepository.getAllAutoServiceCompany(), current);
        autoRepository.deleteAll();
   }
    @Test
    void getAllVinTest()
    {
        List<String> current = new ArrayList<String>();
        Auto a = new Auto("22222222222222221", "SC-1", new ServiceCompany("SC-1"));
        Auto b = new Auto("22222222222222223", "SC-2", new ServiceCompany("SC-2"));
        Auto c = new Auto("22222222222222224", "SC-3", new ServiceCompany("SC-3"));
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
        Auto a = new Auto("22222222222222221", "SC-1", new ServiceCompany("SC-2"));
        Auto b = new Auto("22222222222222223", "SC-2", new ServiceCompany("SC-2"));
        Auto c = new Auto("22222222222222224", "SC-3", new ServiceCompany("SC-2"));
        autoRepository.create(a);
        autoRepository.create(b);
        autoRepository.create(c);
        Assertions.assertEquals(autoRepository.deleteAll(), true);

    }
    @Test
    void deleteTest()
    {
        Auto test = new Auto("11111111111111115", "SC-5", new ServiceCompany("SC-5"));
        autoRepository.create(test);
        Assertions.assertEquals(autoRepository.delete(test), true);
        autoRepository.deleteAll();
    }
    @Test
    void updatetest()
    {
        Auto test = new Auto("11111111111111115", "SC-5", new ServiceCompany("SC-5"));
        autoRepository.create(test);
        Auto test1 = new Auto("11111111111111115", "SC-6", new ServiceCompany("SC-6"));
        Assertions.assertEquals(autoRepository.update(test1), true);
        autoRepository.delete(test1);
    }
    @Test
    void doesCarToServiceCompanyRepositoryTest()
    {
        AutoService testService = new AutoService();
        ServiceCompany testServiceCompany = new ServiceCompany("SC-222");
        serviceCompanyRepository.create(testServiceCompany);
        testService.addAuto("11111", "SC-222");
        Auto testAuto = new Auto("11111", "SC-222", new ServiceCompany("SC-222"));
        Assertions.assertEquals(autoRepository.doesCarToServiceCompanyRepository(testAuto), true);
    }
}
