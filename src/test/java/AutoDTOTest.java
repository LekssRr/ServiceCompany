import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.DTO.AutoDTO;
import ru.warrantyauto.DTO.ServiceCompanyDTO;

public class AutoDTOTest {

    @Test
    void getVinTest()
    {
        AutoDTO testAuto = new AutoDTO("12345678910", "SC-100", new ServiceCompanyDTO("SC-100"));
        Assertions.assertEquals(testAuto.getVin(), "12345678910");
    }
    @Test
    void getServiceCompanTest()
    {
        AutoDTO testAuto = new AutoDTO("12345678910", "SC-100", new ServiceCompanyDTO("SC-100"));
        Assertions.assertEquals(testAuto.getServiceCompan().toString(), new ServiceCompanyDTO("SC-100").toString());
    }
    @Test
    void getServiceCompanyName()
    {
        AutoDTO testAuto = new AutoDTO("12345678910", "SC-100", new ServiceCompanyDTO("SC-100"));
        Assertions.assertEquals(testAuto.getNameServiceCompany(), new ServiceCompanyDTO("SC-100").getName());
    }
}
