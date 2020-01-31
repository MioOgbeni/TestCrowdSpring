package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.test_case.TestResultImp;
import cz.spitsoft.testcrowd.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImp implements TestResultService {

    @Autowired
    private TestResultRepository testResultRepository;

    @Override
    public long count() {
        return testResultRepository.count();
    }

    @Override
    public void save(TestResultImp testCase) {
        testResultRepository.save(testCase);
    }

    @Override
    public void delete(TestResultImp testCase) {
        testResultRepository.delete(testCase);
    }

    @Override
    public List<TestResultImp> findAll() {
        return testResultRepository.findAll();
    }

    @Override
    public Page<TestResultImp> findAll(Pageable pageable) {
        return testResultRepository.findAll(pageable);
    }

    @Override
    public TestResultImp findById(String id) {
        return testResultRepository.findById(id);
    }

}
