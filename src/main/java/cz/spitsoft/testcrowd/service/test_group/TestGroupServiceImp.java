package cz.spitsoft.testcrowd.service.test_group;

import cz.spitsoft.testcrowd.model.test_case.TestGroupImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.repository.TestGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TestGroupServiceImp implements TestGroupService {

    /*@Autowired
    private TestGroupRepository testGroupRepository;

    @Override
    public long count() {
        return testGroupRepository.count();
    }

    @Override
    public void save(TestGroupImp testCase) {
        testGroupRepository.save(testCase);
    }

    @Override
    public void delete(TestGroupImp testCase) {
        testGroupRepository.delete(testCase);
    }

    @Override
    public List<TestGroupImp> findAll() {
        return testGroupRepository.findAll();
    }

    @Override
    public Page<TestGroupImp> findAll(Pageable pageable) {
        return testGroupRepository.findAll(pageable);
    }

    @Override
    public List<TestGroupImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy) {
        return testGroupRepository.findAllAvailableToBeforeAndCreatedBy(availableTo, createdBy);
    }

    @Override
    public Page<TestGroupImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy, Pageable pageable) {
        return testGroupRepository.findAllAvailableToBeforeAndCreatedBy(availableTo, createdBy, pageable);
    }

    @Override
    public List<TestGroupImp> findAllAvailableToBefore(Date availableTo) {
        return testGroupRepository.findAllAvailableToBefore(availableTo);
    }

    @Override
    public Page<TestGroupImp> findAllAvailableToBefore(Date availableTo, Pageable pageable) {
        return testGroupRepository.findAllAvailableToBefore(availableTo, pageable);
    }

    @Override
    public TestGroupImp findById(String id) {
        return testGroupRepository.findById(id);
    }

    @Override
    public TestGroupImp findByName(String name) {
        return testGroupRepository.findByName(name);
    }*/
}
