package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.test_case.TestCase;
import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
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

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean isCurrentUserAuthorOrAdmin(TestCase testCase) {
        String testCaseAuthorId = testCase.getCreatedBy().getId();
        String currentUserId = securityService.getCurrentUser().getId();
        return currentUserId.equals(testCaseAuthorId) || securityService.isCurrentUserAdmin();
    }

    @Override
    public boolean isCurrentUserAuthorOrTester(TestCase testCase) {
        String testCaseAuthorId = testCase.getCreatedBy().getId();
        String currentUserId = securityService.getCurrentUser().getId();
        return currentUserId.equals(testCaseAuthorId) || securityService.isCurrentUserTester();
    }

    @Override
    public long count() {
        return testCaseRepository.count();
    }

    @Override
    public void save(TestCaseImp testCase) {
        testCaseRepository.save(testCase);
    }

    @Override
    public void delete(TestCaseImp testCase) {
        testCaseRepository.delete(testCase);
    }

    @Override
    public List<TestCaseImp> findAll() {
        return testCaseRepository.findAll();
    }

    @Override
    public Page<TestCaseImp> findAll(Pageable pageable, String name) {
        return testCaseRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<TestCaseImp> findByCreatedBy(UserImp user, Pageable pageable, String name) {
        return testCaseRepository.findByCreatedByAndNameContaining(user, name, pageable);
    }

    /*@Override
    public List<TestCaseImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy) {
        return testCaseRepository.findAllAvailableToBeforeAndCreatedBy(availableTo, createdBy);
    }*/

    /*@Override
    public Page<TestCaseImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy, Pageable pageable) {
        return testCaseRepository.findAllAvailableToBeforeAndCreatedBy(availableTo, createdBy, pageable);
    }*/

    /*@Override
    public List<TestCaseImp> findAllAvailableToBefore(Date availableTo) {
        return testCaseRepository.findAllAvailableToBefore(availableTo);
    }*/

    /*@Override
    public Page<TestCaseImp> findAllAvailableToBefore(Date availableTo, Pageable pageable) {
        return testCaseRepository.findAllAvailableToBefore(availableTo, pageable);
    }*/

    @Override
    public TestCaseImp findById(String id) {
        return testCaseRepository.findById(id);
    }

    @Override
    public TestCaseImp findByName(String name) {
        return testCaseRepository.findByName(name);
    }

}
