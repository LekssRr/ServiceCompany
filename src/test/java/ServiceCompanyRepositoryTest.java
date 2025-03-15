import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;

public class ServiceCompanyRepositoryTest {
    @Test
    void createTest()
    {
        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
        ServiceCompany sc = new ServiceCompany("SC-7");
        ArrayList<String> test = new ArrayList<>();
        test.add("fsdqqfsdfs");
        test.add("sdada;;;sdada");
        sc.setAllVin(test);
        Assertions.assertEquals(testRepository.create(sc), true);
        //testRepository.delete(sc);
    }
    @Test
    void getVinToServiceCompany()
    {
        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
        ServiceCompany sc = new ServiceCompany("SC-7");
        ArrayList<String> test = new ArrayList<>();
        test.add("fsdfsdfs");
        test.add("sdadasdada");
        sc.setAllVin(test);
        testRepository.create(sc);
        Assertions.assertEquals(testRepository.getAllAutoToServiceCompany(sc), test);
        testRepository.delete(sc);
    }
    @Test
    void deleteTest()
    {
        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
        ServiceCompany sc = new ServiceCompany("SC-7");
        ArrayList<String> test = new ArrayList<>();
        test.add("fsdfsdfs");
        test.add("sdadasdada");
        sc.setAllVin(test);
        testRepository.create(sc);
        Assertions.assertEquals(testRepository.delete(sc), true);
    }
    @Test
    void updateTest()
    {
        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
        ServiceCompany sc = new ServiceCompany("SC-7");
        ArrayList<String> test = new ArrayList<>();
        test.add("12345");
        test.add("54673");
        test.add("232153");
        sc.setAllVin(test);
        testRepository.update(sc);
        Assertions.assertEquals(testRepository.update(sc), true);
        testRepository.delete(sc);
    }
}
