import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.ServiceCompanyRepository;
import ru.warrantyauto.sevice.AutoService;
import ru.warrantyauto.sevice.ServiceCompanySevice;

import java.util.ArrayList;

public class ServiceCompanyServiceTest {

    @Test
    void addServiceCompanyTest()
    {
        ServiceCompanySevice testServiceCompanyService = new ServiceCompanySevice();
        ServiceCompanyRepository testServiceCompanyRepository = new ServiceCompanyRepository();
        ServiceCompany sc = new ServiceCompany("SC-7");
        ServiceCompany sc1 = new ServiceCompany("SC-5");
        ServiceCompany sc2 = new ServiceCompany("SC-2");
        testServiceCompanyRepository.create(sc);
        testServiceCompanyRepository.create(sc1);
        testServiceCompanyRepository.create(sc2);
        testServiceCompanyRepository.delete(new ServiceCompany("SC-1"));
        Assertions.assertEquals(testServiceCompanyService.addServiceCompany("SC-7"), false);
        Assertions.assertEquals(testServiceCompanyService.addServiceCompany("SC-1"), true);
        testServiceCompanyRepository.delete(new ServiceCompany("SC-1"));
        testServiceCompanyRepository.delete(new ServiceCompany("SC-7"));
        testServiceCompanyRepository.delete(new ServiceCompany("SC-5"));
        testServiceCompanyRepository.delete(new ServiceCompany("SC-2"));
        testServiceCompanyRepository.deleteAllServiceCompany();
    }
    @Test
    void deleteServiceCompany()
    {
        ServiceCompanySevice testServiceCompanyService = new ServiceCompanySevice();
        ServiceCompanyRepository testServiceCompanyRepository = new ServiceCompanyRepository();
        ServiceCompany sc = new ServiceCompany("SC-7");
        ServiceCompany sc1 = new ServiceCompany("SC-5");
        ServiceCompany sc2 = new ServiceCompany("SC-2");
        testServiceCompanyRepository.create(sc);
        testServiceCompanyRepository.create(sc1);
        testServiceCompanyRepository.create(sc2);
        testServiceCompanyService.deleteServiceCompany("SC-1");
        Assertions.assertEquals(testServiceCompanyService.deleteServiceCompany("SC-7"), true);
        Assertions.assertEquals(testServiceCompanyService.deleteServiceCompany("SC-1"), false);
        testServiceCompanyService.deleteServiceCompany("SC-2");
        testServiceCompanyService.deleteServiceCompany("SC-5");
        testServiceCompanyRepository.deleteAllServiceCompany();
    }
    @Test
    void updateServiceCompanyServiceTest()
    {
        AutoRepository testAutoRepository = new AutoRepository();
        ServiceCompanySevice testServiceCompanyService = new ServiceCompanySevice();
        ServiceCompanyRepository testServiceCompanyRepository = new ServiceCompanyRepository();
        ServiceCompany sc = new ServiceCompany("SC-99");
        ArrayList<String> test = new ArrayList<>();
        Auto test1 = new Auto("aaaaaa", "SC-99", new ServiceCompany("SC-99"));
        Auto test2 = new Auto("bbbbbb", "SC-99", new ServiceCompany("SC-99"));
        Auto test3 = new Auto("vvvvvv", "SC-99", new ServiceCompany("SC-99"));
        test.add("aaaaaa");
        test.add("bbbbbb");
        test.add("vvvvvv");
        sc.setAllVin(test);
        testAutoRepository.create(test1);
        testAutoRepository.create(test2);
        testAutoRepository.create(test3);
        testServiceCompanyRepository.create(sc);
        Assertions.assertEquals(testServiceCompanyService.updateServiceCompany("SC-99", "SC-8"), true);
        testServiceCompanyRepository.deleteAllServiceCompany();
        testAutoRepository.deleteAll();
    }

}
