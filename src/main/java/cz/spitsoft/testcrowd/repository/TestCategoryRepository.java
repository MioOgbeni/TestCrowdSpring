package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.testcases.TestCategoryImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface TestCategoryRepository extends JpaRepository<TestCategoryImp, UUID> {

    //@Transactional(readOnly = true)
    //List<SoftwareTypeImp> findByValidTrue();

    //@Transactional(readOnly = true)
    //List<SoftwareTypeImp> findByValidFalse();

    //@Transactional(readOnly = true)
    //TestCategoryImp findByName(String name);

    //@Transactional(readOnly = true)
    //List<SoftwareTypeImp> findByModifiedBy(UserImp user);
}