package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

@Repository
public interface SoftwareTypeRepository extends JpaRepository<SoftwareTypeImp, UUID> {

    @Nonnull
    List<SoftwareTypeImp> findAll();

    @Nonnull
    Page<SoftwareTypeImp> findAll(@Nonnull Pageable pageable);

    SoftwareTypeImp findById(String id);

    @Transactional(readOnly = true)
    SoftwareTypeImp findByName(String name);

    @Transactional(readOnly = true)
    List<SoftwareTypeImp> findByEnabledTrue();

    @Transactional(readOnly = true)
    List<SoftwareTypeImp> findByEnabledFalse();

    @Transactional(readOnly = true)
    List<SoftwareTypeImp> findByUpdatedBy(UserImp user);

    @Transactional(readOnly = true)
    List<SoftwareTypeImp> findByCreatedBy(UserImp user);

}
