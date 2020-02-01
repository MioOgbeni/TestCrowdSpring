package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    long count();

    void save(UserImp user);

    void delete(UserImp user);

    List<UserImp> findAll();

    Page<UserImp> findAll(Pageable pageable, String userName);

    UserImp findById(String id);

    UserImp findByUsername(String username);

    UserImp findByEmail(String username);

}
