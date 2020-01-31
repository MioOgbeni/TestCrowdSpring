package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.test_case.TestResultImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestResultService {

    long count();

    TestResultImp save(TestResultImp testResult);

    void delete(TestResultImp testResult);

    List<TestResultImp> findAll();

    Page<TestResultImp> findAll(Pageable pageable);

    Page<TestResultImp> findByUser(UserImp user, Pageable pageable);

    TestResultImp findById(String id);

}
