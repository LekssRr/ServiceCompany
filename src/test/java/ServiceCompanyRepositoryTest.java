import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.warrantyauto.entities.Auto;
import ru.warrantyauto.entities.ServiceCompany;
import ru.warrantyauto.repository.AutoRepository;
import ru.warrantyauto.repository.ServiceCompanyRepository;

import java.util.ArrayList;

public class ServiceCompanyRepositoryTest {

//    @Test
//    void addVinToServiceCompanyTest()
//    {
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        ServiceCompany sc = new ServiceCompany("SC-7");
//        testRepository.deleteAllServiceCompany();
//        ArrayList<String> vinListSC = new ArrayList<>();
//        sc.setAllVin(vinListSC);
//        testRepository.create(sc);
//        Auto one = new Auto("7777777", "SC-7", new ServiceCompany("SC-7"));
//        Auto one1 = new Auto("7771777", "SC-7", new ServiceCompany("SC-7"));
//        Auto one2 = new Auto("2222", "SC-6", new ServiceCompany("SC-6"));
//        Auto one3 = new Auto("22222", "SC-6", new ServiceCompany("SC-6"));
//        Auto one4 = new Auto("333333", "SC-5", new ServiceCompany("SC-5"));
//        Auto one5 = new Auto("777133333777", "SC-5", new ServiceCompany("SC-5"));
//        Auto one6 = new Auto("777744444777", "SC-1", new ServiceCompany("SC-1"));
//        Auto one7 = new Auto("77744441777", "SC-1", new ServiceCompany("SC-1"));
//        Auto one8 = new Auto("77744441772227", "SC-999", new ServiceCompany("SC-999"));
//        Assertions.assertEquals(testRepository.addVinToServiceCompany(new Auto("7777777", "SC-7", new ServiceCompany("SC-7"))), true);
//        Assertions.assertEquals(testRepository.addVinToServiceCompany(new Auto("7771777", "SC-7", new ServiceCompany("SC-7"))), true);
//        Assertions.assertEquals(testRepository.addVinToServiceCompany(new Auto("2222", "SC-6", new ServiceCompany("SC-6"))), true);
//        Assertions.assertEquals(testRepository.addVinToServiceCompany(new Auto("22222", "SC-6", new ServiceCompany("SC-6"))), true);
//        Assertions.assertEquals(testRepository.addVinToServiceCompany(new Auto("333333", "SC-5", new ServiceCompany("SC-5"))), true);
//        //testRepository.deleteVinToServiceCompany(new Auto("2222", "SC-6", new ServiceCompany("SC-6")));
//        //Assertions.assertEquals(testRepository.addVinToServiceCompany(new Auto("777133333777", "SC-5", new ServiceCompany("SC-5"))), true);
//        //Assertions.assertEquals(testRepository.addVinToServiceCompany(new Auto("77744441772227", "SC-999", new ServiceCompany("SC-999"))), true);
//
//        testRepository.deleteAllServiceCompany();
//        AutoRepository test = new AutoRepository();
//
//        test.delete(one);
//        test.delete(one1);
//        test.delete(one2);
//        test.delete(one3);
//        test.delete(one4);
//        test.delete(one5);
//        test.delete(one6);
//        test.delete(one7);
//        test.delete(one8);
//
//    }
//    @Test
//    void deleteVinToServiceCompany()
//    {
//
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        testRepository.deleteAllServiceCompany();
//        ServiceCompany sc = new ServiceCompany("SC-7");
//        ArrayList<String> vinListSC = new ArrayList<>();
//        vinListSC.add("1111111");
//        vinListSC.add("12312312");
//        vinListSC.add("2222222");
//        sc.setAllVin(vinListSC);
//        testRepository.create(sc);
//        Assertions.assertEquals(testRepository.deleteVinToServiceCompany(new Auto("2222222", "SC-7", new ServiceCompany("SC-7"))), true);
//        testRepository.delete(sc);
//    }
//    @Test
//    void createTest()
//    {
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        ServiceCompany sc = new ServiceCompany("90909");
//        ArrayList<String> test = new ArrayList<>();
//        test.add("fsdfsdfs");
//        test.add("sdadasdada");
//        sc.setAllVin(test);
//        Assertions.assertEquals(testRepository.create(sc), true);
//        testRepository.delete(sc);
//    }
//    @Test
//    void getVinToServiceCompany()
//    {
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        ServiceCompany sc = new ServiceCompany("SC-999");
//        ArrayList<String> test = new ArrayList<>();
//        test.add("fsdfsdfs");
//        test.add("sdadasdada");
//        sc.setAllVin(test);
//        testRepository.create(sc);
//        Assertions.assertEquals(testRepository.getAllAutoToServiceCompany(sc), test);
//        testRepository.delete(sc);
//    }
//    @Test
//    void deleteTest()
//    {
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        ServiceCompany sc = new ServiceCompany("SC-7");
//        ArrayList<String> test = new ArrayList<>();
//        test.add("fsdfsdfs");
//        test.add("sdadasdada");
//        sc.setAllVin(test);
//        testRepository.create(sc);
//        Assertions.assertEquals(testRepository.delete(sc), true);
//    }
//    @Test
//    void updateTest()
//    {
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        ServiceCompany sc = new ServiceCompany("SC-7");
//        ArrayList<String> test = new ArrayList<>();
//        test.add("12345");
//        test.add("54673");
//        test.add("232153");
//        sc.setAllVin(test);
//        testRepository.update(sc);
//        Assertions.assertEquals(testRepository.update(sc), true);
//        testRepository.delete(sc);
//    }
//    @Test
//    void getAllServiceCompanyTest()
//    {
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        ServiceCompany sc = new ServiceCompany("SC-7");
//        ServiceCompany sc1 = new ServiceCompany("SC-5");
//        ServiceCompany sc2 = new ServiceCompany("SC-2");
//        testRepository.create(sc);
//        testRepository.create(sc1);
//        testRepository.create(sc2);
//        ArrayList<String> test = new ArrayList<>();
//        test.add("SC-7");
//        test.add("SC-5");
//        test.add("SC-2");
//        Assertions.assertEquals(testRepository.getAllServiceCompany(), test);
//    }
//    @Test
//    void deleteAllServiceCompany()
//    {
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        ServiceCompany sc = new ServiceCompany("SC-7");
//        ServiceCompany sc1 = new ServiceCompany("SC-5");
//        ServiceCompany sc2 = new ServiceCompany("SC-2");
//        testRepository.create(sc);
//        testRepository.create(sc1);
//        testRepository.create(sc2);
//        Assertions.assertEquals(testRepository.deleteAllServiceCompany(), true);
//    }
//    @Test
//    void updateServicCompanyTest()
//    {
//        ServiceCompanyRepository testRepository = new ServiceCompanyRepository();
//        ServiceCompany serviceCompanyOld = new ServiceCompany("SC-22");
//        ServiceCompany serviceCompanyNew = new ServiceCompany("SC-33");
//        ArrayList<String> vinList= new ArrayList<>();
//        vinList.add("11111");
//        vinList.add("22222");
//        vinList.add("33333");
//        serviceCompanyOld.setAllVin(vinList);
//        testRepository.create(serviceCompanyOld);
//        Assertions.assertEquals(testRepository.updateServiceCompany(serviceCompanyOld, serviceCompanyNew), true);
//        //testRepository.deleteAllServiceCompany();
//    }


}
