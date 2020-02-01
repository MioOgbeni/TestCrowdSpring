package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.repository.TestCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCategoryServiceImp implements TestCategoryService {

    @Autowired
    private TestCategoryRepository testCategoryRepository;

    @Override
    public long count() {
        return testCategoryRepository.count();
    }

    @Override
    public void save(TestCategoryImp testCategory) {
        testCategoryRepository.save(testCategory);
    }

    @Override
    public void delete(TestCategoryImp testCategory) {
        testCategoryRepository.delete(testCategory);
    }

    @Override
    public List<TestCategoryImp> findAll() {
        return testCategoryRepository.findAll();
    }

    @Override
    public Page<TestCategoryImp> findAll(Pageable pageable, String name) {
        return testCategoryRepository.findByNameContaining(name, pageable);
    }

    @Override
    public TestCategoryImp findById(String id) {
        return testCategoryRepository.findById(id);
    }

    @Override
    public TestCategoryImp findByName(String name) {
        return testCategoryRepository.findByName(name);
    }

    @Override
    public List<TestCategoryImp> findByEnabledTrue() {
        return testCategoryRepository.findByEnabledTrue();
    }

    @Override
    public List<TestCategoryImp> findByEnabledFalse() {
        return testCategoryRepository.findByEnabledFalse();
    }

    @Override
    public List<TestCategoryImp> findByUpdatedBy(UserImp user) {
        return testCategoryRepository.findByUpdatedBy(user);
    }

    @Override
    public List<TestCategoryImp> findByCreatedBy(UserImp user) {
        return testCategoryRepository.findByCreatedBy(user);
    }

}
