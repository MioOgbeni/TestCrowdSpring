package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCaseImp, UUID> {

    @Transactional(readOnly = true)
    List<TestCaseImp> findByName(String name);
}
