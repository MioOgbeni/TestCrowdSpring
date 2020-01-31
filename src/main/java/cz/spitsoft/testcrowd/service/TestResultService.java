package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.test_case.TestResultImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestResultService {

    long count();

    void save(TestResultImp testCase);

    void delete(TestResultImp testCase);

    List<TestResultImp> findAll();

    Page<TestResultImp> findAll(Pageable pageable);

    TestResultImp findById(String id);

}
