import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.ServiceCompanyRepository;
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
        ServiceCompanySevice testServiceCompanyService = new ServiceCompanySevice();
        ServiceCompanyRepository testServiceCompanyRepository = new ServiceCompanyRepository();
        ServiceCompany sc = new ServiceCompany("SC-7");
        testServiceCompanyRepository.create(sc);
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        test.add("test1");
        test.add("tes2");
        sc.setAllVin(test);
        Assertions.assertEquals(testServiceCompanyService.updateServiceCompany("SC-7"), true);
        testServiceCompanyRepository.deleteAllServiceCompany();
    }

}
