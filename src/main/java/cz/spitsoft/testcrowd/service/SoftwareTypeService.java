package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SoftwareTypeService {

    long count();

    void save(SoftwareTypeImp softwareType);

    void delete(SoftwareTypeImp softwareType);

    List<SoftwareTypeImp> findAll();

    Page<SoftwareTypeImp> findAll(Pageable pageable);

    SoftwareTypeImp findById(String id);

    SoftwareTypeImp findByName(String name);

    List<SoftwareTypeImp> findByEnabledTrue();

    List<SoftwareTypeImp> findByEnabledFalse();

    List<SoftwareTypeImp> findByCreatedBy(UserImp user);

    List<SoftwareTypeImp> findByUpdatedBy(UserImp user);

}
