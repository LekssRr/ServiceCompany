package Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.ServiceCompanyRepository;
import ru.warrantyauto.sevice.AutoService;
import ru.warrantyauto.sevice.ServiceCompanySevice;

import java.util.ArrayList;

public class TestServiceCompany {

    AutoRepository autoRepository = Mockito.mock(AutoRepository.class);

    ServiceCompanyRepository serviceCompanyRepository = Mockito.mock(ServiceCompanyRepository.class);

    AutoService autoService = new AutoService(autoRepository, serviceCompanyRepository);

    ServiceCompanySevice serviceCompanySevice = new ServiceCompanySevice(serviceCompanyRepository, autoRepository);
    @Test
    void addServiceCompanyTest()
    {
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-7");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-5");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-2");
        ArrayList<String> testL= new ArrayList<>();
        testL.add(sc.getName());
        testL.add(sc1.getName());
        testL.add(sc2.getName());
        Mockito.when(serviceCompanyRepository.getAllServiceCompany()).thenReturn(testL);
        serviceCompanySevice.addServiceCompany(sc.getName());
        serviceCompanySevice.addServiceCompany(sc1.getName());
        serviceCompanySevice.addServiceCompany(sc2.getName());
        Assertions.assertEquals(serviceCompanySevice.addServiceCompany("SC-7"), false);
        Assertions.assertEquals(serviceCompanySevice.addServiceCompany("SC-1"), true);
    }
    @Test
    void deleteServiceCompany()
    {

        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-7");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-5");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-2");
        ArrayList<String> testL= new ArrayList<>();
        testL.add(sc.getName());
        testL.add(sc1.getName());
        testL.add(sc2.getName());
        Mockito.when(serviceCompanyRepository.getAllServiceCompany()).thenReturn(testL);
        serviceCompanySevice.addServiceCompany(sc.getName());
        serviceCompanySevice.addServiceCompany(sc1.getName());
        serviceCompanySevice.addServiceCompany(sc2.getName());
        Assertions.assertEquals(serviceCompanySevice.deleteServiceCompany("SC-7"), true);
        Assertions.assertEquals(serviceCompanySevice.deleteServiceCompany("SC-1"), false);
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
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-5");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-2");
        ArrayList<String> testL= new ArrayList<>();
        testL.add(sc.getName());
        testL.add(sc1.getName());
        testL.add(sc2.getName());
        Mockito.when(serviceCompanyRepository.getAllServiceCompany()).thenReturn(testL);

        Mockito.when(serviceCompanyRepository.getAllServiceCompany()).thenReturn(testL);
        Mockito.when(serviceCompanyRepository.getAllAutoToServiceCompany(sc)).thenReturn(test);
        Assertions.assertEquals(serviceCompanySevice.updateServiceCompany("SC-99", "SC-8"), true);
    }
    @Test
    void doesCarToServiceCompanyServiceTest()
    {
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-99");
        AutoEntity a = new AutoEntity("11111111111111111", sc.getName(), sc);
        Mockito.when(autoRepository.doesCarToServiceCompanyRepository(a)).thenReturn(false);
        Assertions.assertEquals(autoService.doesCarToServiceCompany(a.getVin(), a.getNameServiceCompany()), false);
    }
}
