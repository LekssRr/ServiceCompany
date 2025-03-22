package Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entity.ServiceCompanyEntity;

import java.util.ArrayList;

public class ServiceCompanyEntityTest {
    @Test
    void getNameTest(){
        ServiceCompanyEntity serviceCompany = new ServiceCompanyEntity("SC-89");
        Assertions.assertEquals(serviceCompany.getName(), "SC-89");
    }
    @Test
    void allVinAutoTest()
    {
        ServiceCompanyEntity serviceCompany = new ServiceCompanyEntity("SC-89");
        ArrayList<String> test = new ArrayList<>();
        test.add("1");
        test.add("2");
        test.add("3");
        serviceCompany.setAllVin(test);
        Assertions.assertEquals(serviceCompany.allVinAuto(), test);
    }
    @Test
    void toStringTest()
    {
        ServiceCompanyEntity serviceCompany = new ServiceCompanyEntity("SC-89");
        Assertions.assertEquals(serviceCompany.toString(), "SC-89");
    }

}
