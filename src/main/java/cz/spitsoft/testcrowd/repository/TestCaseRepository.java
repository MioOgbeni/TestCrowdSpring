package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCaseImp, UUID> {

    List<TestCaseImp> findAll();

    Page<TestCaseImp> findAll(Pageable pageable);

    List<TestCaseImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy);

    Page<TestCaseImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy, Pageable pageable);

    List<TestCaseImp> findAllAvailableToBefore(Date availableTo);

    Page<TestCaseImp> findAllAvailableToBefore(Date availableTo, Pageable pageable);

    TestCaseImp findById(String id);

    @Transactional(readOnly = true)
    TestCaseImp findByName(String name);

}
