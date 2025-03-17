import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;

import javax.accessibility.Accessible;

public class AutoEntityTest {

    @Test
    void getVinTest()
    {
        Auto testAuto = new Auto("12345678910", "SC-100", new ServiceCompany("SC-100"));
        Assertions.assertEquals(testAuto.getVin(), "12345678910");
    }
    @Test
    void getServiceCompanTest()
    {
        Auto testAuto = new Auto("12345678910", "SC-100", new ServiceCompany("SC-100"));
        Assertions.assertEquals(testAuto.getServiceCompan().toString(), new ServiceCompany("SC-100").toString());
    }
    @Test
    void getServiceCompanyName()
    {
        Auto testAuto = new Auto("12345678910", "SC-100", new ServiceCompany("SC-100"));
        Assertions.assertEquals(testAuto.getNameServiceCompany(), new ServiceCompany("SC-100").getName());
    }
}
