package cz.spitsoft.testcrowd.service.test_case;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestCaseService {

    long count();

    void save(TestCaseImp testCategory);

    void delete(TestCaseImp testCategory);

    List<TestCaseImp> findAll();

    Page<TestCaseImp> findAll(Pageable pageable);

    TestCaseImp findById(String id);

    TestCaseImp findByName(String name);

}
