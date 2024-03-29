package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.test_case.TestResultImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

@Repository
public interface TestResultRepository extends JpaRepository<TestResultImp, UUID> {

    @Nonnull
    List<TestResultImp> findAll();

    @Nonnull
    Page<TestResultImp> findAll(@Nonnull Pageable pageable);

    Page<TestResultImp> findByUser(UserImp user, Pageable pageable);

    Page<TestResultImp> findByTestCase(TestCaseImp testCase, Pageable pageable);

    Page<TestResultImp> findByTestCaseAndUser(TestCaseImp testCase, UserImp user, Pageable pageable);

    TestResultImp findById(String id);

}
