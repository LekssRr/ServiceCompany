import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entity.AutoEntity;
import ru.warrantyauto.entity.ServiceCompanyEntity;

public class AutoEntityTest {

    @Test
    void getVinTest()
    {
        AutoEntity testAuto = new AutoEntity("12345678910", "SC-100", new ServiceCompanyEntity("SC-100"));
        Assertions.assertEquals(testAuto.getVin(), "12345678910");
    }
    @Test
    void getServiceCompanTest()
    {
        AutoEntity testAuto = new AutoEntity("12345678910", "SC-100", new ServiceCompanyEntity("SC-100"));
        Assertions.assertEquals(testAuto.getServiceCompan().toString(), new ServiceCompanyEntity("SC-100").toString());
    }
    @Test
    void getServiceCompanyName()
    {
        AutoEntity testAuto = new AutoEntity("12345678910", "SC-100", new ServiceCompanyEntity("SC-100"));
        Assertions.assertEquals(testAuto.getNameServiceCompany(), new ServiceCompanyEntity("SC-100").getName());
    }
}
