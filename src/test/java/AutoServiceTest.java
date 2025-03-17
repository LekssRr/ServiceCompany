import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.ServiceCompanyRepository;
import ru.warrantyauto.sevice.AutoService;

import java.util.ArrayList;

public class AutoServiceTest {

    @Test
    public void deleteAutoTest()
    {
        AutoService testService = new AutoService();
        new Auto("22222222222222222", "SC-2", new ServiceCompany("SC-2"));
        testService.addAuto("22222222222222222", "SC-2");
        Assertions.assertEquals(testService.deleteAuto("22222222222222222"), true);

    }
    @Test
    public void addAutoTest()
    {
        AutoService testService = new AutoService();
        ServiceCompanyRepository testServiceCompanyRepository = new ServiceCompanyRepository();
        testServiceCompanyRepository.create(new ServiceCompany("SC-1"));
        AutoRepository autoRepository = new AutoRepository();
        Assertions.assertEquals(testService.addAuto("11111", "SC-1"), true);
        Assertions.assertEquals(testService.addAuto("11121", "SC-1"), true);
        Assertions.assertEquals(testService.addAuto("13111", "SC-1"), true);
        testServiceCompanyRepository.delete(new ServiceCompany("SC-1"));
        testService.deleteAuto("11111");
        testService.deleteAuto("11121");
        testService.deleteAuto("13111");
        autoRepository.deleteAll();
    }
    @Test
    public void updateAutoTest()
    {
        AutoService testService = new AutoService();
        AutoRepository testRep = new AutoRepository();
        ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
        ServiceCompany testServiceCompany = new ServiceCompany("SC-1");
        serviceCompanyRepository.create(testServiceCompany);
        testService.addAuto("22222", "SC-1");


        Assertions.assertEquals(testService.updateAuto("22222", "SC-2"), true);

        testService.deleteAuto("22222");
        serviceCompanyRepository.delete(testServiceCompany);
        serviceCompanyRepository.deleteAllServiceCompany();
        testRep.deleteAll();
    }
    @Test
    void doesCarExistTest()
    {
        AutoService testService = new AutoService();
        ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
        ServiceCompany testServiceCompany = new ServiceCompany("SC-222");
        serviceCompanyRepository.create(testServiceCompany);
        testService.addAuto("33333333333333331", "SC-222");
        Auto testAuto = new Auto("33333333333333331", "SC-222", new ServiceCompany("SC-222"));
        Assertions.assertEquals(testService.doesCarExist("33333333333333331"), true);
        testService.deleteAuto("33333333333333331");
        serviceCompanyRepository.delete(testServiceCompany);
        AutoRepository testRep = new AutoRepository();
        testRep.deleteAll();
    }
    @Test
    void getAllAutoTest()
    {

        AutoService testService = new AutoService();
        AutoRepository testRep = new AutoRepository();
        ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
        ServiceCompany testServiceCompany = new ServiceCompany("SC-1");
        ServiceCompany testServiceCompany1 = new ServiceCompany("SC-2");
        ServiceCompany testServiceCompany2 = new ServiceCompany("SC-3");
        serviceCompanyRepository.create(testServiceCompany);
        serviceCompanyRepository.create(testServiceCompany1);
        serviceCompanyRepository.create(testServiceCompany2);
        testService.addAuto("33333333333333331", "SC-1");
        testService.addAuto("33333333334333331", "SC-2");
        ArrayList<String> test = new ArrayList<>();
        test.add("33333333333333331");
        test.add("33333333334333331");

        Assertions.assertEquals(testService.getAllAuto(), test);
        serviceCompanyRepository.deleteAllServiceCompany();
        testRep.deleteAll();
    }
    @Test
    void getServiceCompanyToVinTest()
    {
        AutoService autoService = new AutoService();
        ServiceCompany serviceCompany = new ServiceCompany("SC-22");
        ServiceCompanyRepository serviceCompanyRepository = new ServiceCompanyRepository();
        serviceCompanyRepository.create(serviceCompany);
        autoService.addAuto("11111111111111115", "SC-22");
        Assertions.assertEquals(autoService.getServiceCompanyToVin("11111111111111115"), "SC-22");
        autoService.deleteAuto("11111111111111115");
        serviceCompanyRepository.deleteAllServiceCompany();
    }
}
