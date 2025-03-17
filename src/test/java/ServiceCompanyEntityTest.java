import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entities.ServiceCompany;

import java.util.ArrayList;

public class ServiceCompanyEntityTest {
    @Test
    void getNameTest(){
        ServiceCompany serviceCompany = new ServiceCompany("SC-89");
        Assertions.assertEquals(serviceCompany.getName(), "SC-89");
    }
    @Test
    void allVinAutoTest()
    {
        ServiceCompany serviceCompany = new ServiceCompany("SC-89");
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
        ServiceCompany serviceCompany = new ServiceCompany("SC-89");
        Assertions.assertEquals(serviceCompany.toString(), "SC-89");
    }

}
