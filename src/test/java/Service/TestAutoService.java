package Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;
import ru.warrantyauto.config.DBConnectionProvider;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.ServiceCompanyRepository;
import ru.warrantyauto.sevice.AutoService;
import ru.warrantyauto.sevice.ServiceCompanySevice;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestAutoService {

    AutoRepository autoRepository = Mockito.mock(AutoRepository.class);
    ServiceCompanyRepository serviceCompanyRepository = Mockito.mock(ServiceCompanyRepository.class);

    AutoService autoService = new AutoService(autoRepository, serviceCompanyRepository);



    ServiceCompanySevice serviceCompanySevice = new ServiceCompanySevice(serviceCompanyRepository, autoRepository);
    @Test
    public void deleteAutoTest()
    {
        AutoEntity test  = new AutoEntity("22222222222222222", "SC-2", new ServiceCompanyEntity("SC-2"));
        Mockito.when(serviceCompanyRepository.deleteVinToServiceCompany(test)).thenReturn(true);
        autoService.addAuto("22222222222222222", "SC-2");
        Assertions.assertEquals(autoService.deleteAuto("22222222222222222"), false);

    }
    @Test
    public void addAutoTest()
    {
        List<String> testList = new ArrayList<>();
        Mockito.when(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("11111", "SC-1", new ServiceCompanyEntity("SC-1")))).thenReturn(true);
        Mockito.when(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("11121", "SC-1", new ServiceCompanyEntity("SC-1")))).thenReturn(true);
        Mockito.when(serviceCompanyRepository.addVinToServiceCompany(new AutoEntity("13111", "SC-1", new ServiceCompanyEntity("SC-1")))).thenReturn(true);
        Assertions.assertEquals(autoService.addAuto("11111", "SC-1"), false);
        Assertions.assertEquals(autoService.addAuto("11121", "SC-1"), false);
        Assertions.assertEquals(autoService.addAuto("13111", "SC-1"), false);
    }
    @Test
    public void updateAutoTest()
    {
        AutoEntity test = new AutoEntity("1111", "SC-1", new ServiceCompanyEntity("SC-1"));
        Mockito.when(autoRepository.update(test)).thenReturn(true);
        Assertions.assertEquals(autoService.updateAuto(test.getVin(), test.getNameServiceCompany()), false);
    }
    @Test
    void doesCarExistTest()
    {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("33333333333333331");
        testList.add("33333333333333331");
        Mockito.when(autoRepository.getAllAutoVin()).thenReturn(testList);
        AutoEntity testAuto = new AutoEntity("33333333333333331", "SC-222", new ServiceCompanyEntity("SC-222"));
        Assertions.assertEquals(autoService.doesCarExist("33333333333333331"), true);
    }
    @Test
    void getServiceCompanyToVinTest()
    {
        AutoEntity test = new AutoEntity("11111111111111115", "SC-22", new ServiceCompanyEntity("SC-22"));
        Mockito.when(autoRepository.get(test.getVin())).thenReturn(test.getNameServiceCompany());
        autoService.addAuto("11111111111111115", "SC-22");
        Assertions.assertEquals(autoService.getServiceCompanyToVin(test.getVin()).getName(), new ServiceCompanyDTO("SC-22").toString());
    }

}
