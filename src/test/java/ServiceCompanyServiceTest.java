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
        ServiceCompany sc = new ServiceCompany("SC-7");
        ServiceCompany sc1 = new ServiceCompany("SC-5");
        ServiceCompany sc2 = new ServiceCompany("SC-2");
        testServiceCompanyService.addServiceCompany(sc.getName());
        testServiceCompanyService.addServiceCompany(sc1.getName());
        testServiceCompanyService.addServiceCompany(sc2.getName());
        testServiceCompanyService.deleteServiceCompany("SC-1");
        Assertions.assertEquals(testServiceCompanyService.addServiceCompany("SC-7"), false);
        Assertions.assertEquals(testServiceCompanyService.addServiceCompany("SC-1"), true);
        testServiceCompanyService.deleteServiceCompany("SC-1");
        testServiceCompanyService.deleteServiceCompany("SC-7");
        testServiceCompanyService.deleteServiceCompany("SC-5");
        testServiceCompanyService.deleteServiceCompany("SC-2");
    }
    @Test
    void deleteServiceCompany()
    {
        ServiceCompanySevice testServiceCompanyService = new ServiceCompanySevice();

        ServiceCompany sc = new ServiceCompany("SC-7");
        ServiceCompany sc1 = new ServiceCompany("SC-5");
        ServiceCompany sc2 = new ServiceCompany("SC-2");
        testServiceCompanyService.addServiceCompany(sc.getName());
        testServiceCompanyService.addServiceCompany(sc1.getName());
        testServiceCompanyService.addServiceCompany(sc2.getName());
        testServiceCompanyService.deleteServiceCompany("SC-1");
        Assertions.assertEquals(testServiceCompanyService.deleteServiceCompany("SC-7"), true);
        Assertions.assertEquals(testServiceCompanyService.deleteServiceCompany("SC-1"), false);
        testServiceCompanyService.deleteServiceCompany("SC-2");
        testServiceCompanyService.deleteServiceCompany("SC-5");
    }
    @Test
    void updateServiceCompanyServiceTest()
    {
        ServiceCompanySevice testServiceCompanyService = new ServiceCompanySevice();
        AutoService autoService = new AutoService();
        ServiceCompany sc = new ServiceCompany("SC-99");
        ArrayList<String> test = new ArrayList<>();
        Auto test1 = new Auto("aaaaaa", "SC-99", new ServiceCompany("SC-99"));
        Auto test2 = new Auto("bbbbbb", "SC-99", new ServiceCompany("SC-99"));
        Auto test3 = new Auto("vvvvvv", "SC-99", new ServiceCompany("SC-99"));
        test.add("aaaaaa");
        test.add("bbbbbb");
        test.add("vvvvvv");
        sc.setAllVin(test);
        autoService.addAuto(test1.getVin(), test1.getNameServiceCompany());
        autoService.addAuto(test2.getVin(), test2.getNameServiceCompany());
        autoService.addAuto(test3.getVin(), test3.getNameServiceCompany());
        testServiceCompanyService.addServiceCompany(sc.getName());
        Assertions.assertEquals(testServiceCompanyService.updateServiceCompany("SC-99", "SC-8"), true);
        autoService.deleteAuto(test1.getVin());
        autoService.deleteAuto(test2.getVin());
        autoService.deleteAuto(test3.getVin());
        testServiceCompanyService.deleteServiceCompany("SC-99");
        testServiceCompanyService.deleteServiceCompany("SC-8");
    }
    @Test
    void doesCarToServiceCompanyServiceTest()
    {
        ServiceCompanySevice testServiceCompanyService = new ServiceCompanySevice();
        AutoService autoService = new AutoService();
        ServiceCompany sc = new ServiceCompany("SC-99");
        testServiceCompanyService.addServiceCompany(sc.getName());
        Auto a = new Auto("11111111111111111", sc.getName(), sc);
        Auto b = new Auto("11111111111111121", sc.getName(), sc);
        Auto c = new Auto("11111111111111131", sc.getName(), sc);
        autoService.addAuto(a.getVin(), a.getNameServiceCompany());
        autoService.addAuto(b.getVin(), b.getNameServiceCompany());
        autoService.addAuto(c.getVin(), c.getNameServiceCompany());
        Assertions.assertEquals(autoService.doesCarToServiceCompany(a.getVin(), a.getNameServiceCompany()), true);
    }
}
