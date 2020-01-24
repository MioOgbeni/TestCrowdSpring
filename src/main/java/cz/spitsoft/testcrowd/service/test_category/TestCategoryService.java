package cz.spitsoft.testcrowd.service.test_category;

import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestCategoryService {

    long count();

    void save(TestCategoryImp testCategory);

    void delete(TestCategoryImp testCategory);

    List<TestCategoryImp> findAll();

    Page<TestCategoryImp> findAll(Pageable pageable);

    TestCategoryImp findById(String id);

    TestCategoryImp findByName(String name);

    List<TestCategoryImp> findByEnabledTrue();

    List<TestCategoryImp> findByEnabledFalse();

    List<TestCategoryImp> findByUpdatedBy(UserImp user);

    List<TestCategoryImp> findByCreatedBy(UserImp user);

}
