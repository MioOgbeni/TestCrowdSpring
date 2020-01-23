package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void save(UserImp user);

    void delete(UserImp user);

    long count();

    List<UserImp> findAll();

    Page<UserImp> findAll(Pageable pageable);

    UserImp findByUsername(String username);

    UserImp findByEmail(String username);

    UserImp findById(String id);
}
