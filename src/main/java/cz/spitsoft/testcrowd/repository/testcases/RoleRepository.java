package cz.spitsoft.testcrowd.repository.testcases;

import cz.spitsoft.testcrowd.model.RoleImp;
import cz.spitsoft.testcrowd.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleImp, UUID> {

    @Transactional(readOnly = true)
    RoleImp findByName(RoleType roleType);
}
