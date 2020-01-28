package cz.spitsoft.testcrowd.service.test_case;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TestCaseService {

    long count();

    void save(TestCaseImp testCase);

    void delete(TestCaseImp testCase);

    List<TestCaseImp> findAll();

    Page<TestCaseImp> findAll(Pageable pageable);

    List<TestCaseImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy);

    Page<TestCaseImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy, Pageable pageable);

    List<TestCaseImp> findAllAvailableToBefore(Date availableTo);

    Page<TestCaseImp> findAllAvailableToBefore(Date availableTo, Pageable pageable);

    TestCaseImp findById(String id);

    TestCaseImp findByName(String name);

}
