package cz.spitsoft.testcrowd.repository.testcases;

import cz.spitsoft.testcrowd.model.UserImp;
import cz.spitsoft.testcrowd.model.testcases.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.testcases.TestCategoryImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface TestCategoryRepository extends JpaRepository<TestCategoryImp, UUID> {

    @Transactional(readOnly = true)
    List<SoftwareTypeImp> findByValidTrue();

    @Transactional(readOnly = true)
    List<SoftwareTypeImp> findByValidFalse();

    @Transactional(readOnly = true)
    SoftwareTypeImp findByName(String name);

    @Transactional(readOnly = true)
    List<SoftwareTypeImp> findByModifiedBy(UserImp user);
}
