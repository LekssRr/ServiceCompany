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
        autoRepository.delete(new Auto("22222222222222222", "SC-2", new ServiceCompany("SC-2")));
    }
    @Test
    void getAllServiceCompanyTest()
    {
        ServiceCompany serviceCompany = new ServiceCompany("SC-0001");
        ServiceCompany serviceCompany1 = new ServiceCompany("SC-0002");
        ServiceCompany serviceCompany2 = new ServiceCompany("SC-0003");
        Auto a = new Auto("88888888888888888", serviceCompany.getName(), serviceCompany);
        Auto b = new Auto("88888888888888881", serviceCompany1.getName(), serviceCompany1);
        Auto c = new Auto("88888888888888882", serviceCompany2.getName(), serviceCompany2);
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
        ServiceCompany testServiceCompany = new ServiceCompany("SC-222");


        Auto testAuto = new Auto("77777777777777777", testServiceCompany.getName(), testServiceCompany);
        Auto testAuto1 = new Auto("77777777777777778", testServiceCompany.getName(), testServiceCompany);
        Auto testAuto2 = new Auto("77777777777777787", testServiceCompany.getName(), testServiceCompany);
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
