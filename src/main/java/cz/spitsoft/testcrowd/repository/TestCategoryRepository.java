package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface TestCategoryRepository extends JpaRepository<TestCategoryImp, UUID> {

    TestCategoryImp findById(String id);

    @Transactional(readOnly = true)
    TestCategoryImp findByName(String name);

    @Transactional(readOnly = true)
    List<TestCategoryImp> findByEnabledTrue();

    @Transactional(readOnly = true)
    List<TestCategoryImp> findByEnabledFalse();

    @Transactional(readOnly = true)
    List<TestCategoryImp> findByUpdatedBy(UserImp user);

    @Transactional(readOnly = true)
    List<TestCategoryImp> findByCreatedBy(UserImp user);

}
