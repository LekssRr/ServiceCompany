import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.DTO.ServiceCompanyDTO;

import java.util.ArrayList;

public class ServiceCompanyEntityTest {
    @Test
    void getNameTest(){
        ServiceCompanyDTO serviceCompany = new ServiceCompanyDTO("SC-89");
        Assertions.assertEquals(serviceCompany.getName(), "SC-89");
    }
    @Test
    void allVinAutoTest()
    {
        ServiceCompanyDTO serviceCompany = new ServiceCompanyDTO("SC-89");
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
        ServiceCompanyDTO serviceCompany = new ServiceCompanyDTO("SC-89");
        Assertions.assertEquals(serviceCompany.toString(), "SC-89");
    }

}
