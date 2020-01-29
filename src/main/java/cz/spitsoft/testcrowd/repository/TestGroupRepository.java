package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.test_case.TestGroupImp;
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
public interface TestGroupRepository extends JpaRepository<TestGroupImp, UUID> {

    /*List<TestGroupImp> findAll();

    Page<TestGroupImp> findAll(Pageable pageable);

    List<TestGroupImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy);

    Page<TestGroupImp> findAllAvailableToBeforeAndCreatedBy(Date availableTo, UserImp createdBy, Pageable pageable);

    List<TestGroupImp> findAllAvailableToBefore(Date availableTo);

    Page<TestGroupImp> findAllAvailableToBefore(Date availableTo, Pageable pageable);

    TestGroupImp findById(String id);

    @Transactional(readOnly = true)
    TestGroupImp findByName(String name);*/
}
