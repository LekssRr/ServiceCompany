import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.sevice.AutoService;

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
        Assertions.assertEquals(testService.addAuto("33333333333333331", "SC-1"), true);
        testService.deleteAuto("33333333333333331");
    }
    @Test
    public void updateAutoTest()
    {
        AutoService testService = new AutoService();
        testService.addAuto("33333333333333331", "SC-1");
        Assertions.assertEquals(testService.updateAuto("33333333333333331", "SC-2"), true);
        testService.deleteAuto("33333333333333331");
    }
}
