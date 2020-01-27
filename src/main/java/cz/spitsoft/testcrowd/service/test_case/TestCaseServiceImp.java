package cz.spitsoft.testcrowd.service.test_case;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseServiceImp implements TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public long count() {
        return testCaseRepository.count();
    }

    @Override
    public void save(TestCaseImp testCategory) {
        testCaseRepository.save(testCategory);
    }

    @Override
    public void delete(TestCaseImp testCategory) {
        testCaseRepository.delete(testCategory);
    }

    @Override
    public List<TestCaseImp> findAll() {
        return testCaseRepository.findAll();
    }

    @Override
    public Page<TestCaseImp> findAll(Pageable pageable) {
        return testCaseRepository.findAll(pageable);
    }

    @Override
    public TestCaseImp findById(String id) {
        return testCaseRepository.findById(id);
    }

    @Override
    public TestCaseImp findByName(String name) {
        return testCaseRepository.findByName(name);
    }

}
