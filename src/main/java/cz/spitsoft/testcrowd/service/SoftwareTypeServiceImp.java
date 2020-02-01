package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.repository.SoftwareTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareTypeServiceImp implements SoftwareTypeService {

    @Autowired
    private SoftwareTypeRepository softwareTypeRepository;

    @Override
    public long count() {
        return softwareTypeRepository.count();
    }

    @Override
    public void save(SoftwareTypeImp softwareType) {
        softwareTypeRepository.save(softwareType);
    }

    @Override
    public void delete(SoftwareTypeImp softwareType) {
        softwareTypeRepository.delete(softwareType);
    }

    @Override
    public List<SoftwareTypeImp> findAll() {
        return softwareTypeRepository.findAll();
    }

    @Override
    public Page<SoftwareTypeImp> findAll(Pageable pageable, String name) {
        return softwareTypeRepository.findByNameContaining(name, pageable);
    }

    @Override
    public SoftwareTypeImp findById(String id) {
        return softwareTypeRepository.findById(id);
    }

    @Override
    public SoftwareTypeImp findByName(String name) {
        return softwareTypeRepository.findByName(name);
    }

    @Override
    public List<SoftwareTypeImp> findByEnabledTrue() {
        return softwareTypeRepository.findByEnabledTrue();
    }

    @Override
    public List<SoftwareTypeImp> findByEnabledFalse() {
        return softwareTypeRepository.findByEnabledFalse();
    }

    @Override
    public List<SoftwareTypeImp> findByUpdatedBy(UserImp user) {
        return softwareTypeRepository.findByUpdatedBy(user);
    }

    @Override
    public List<SoftwareTypeImp> findByCreatedBy(UserImp user) {
        return softwareTypeRepository.findByCreatedBy(user);
    }

}
