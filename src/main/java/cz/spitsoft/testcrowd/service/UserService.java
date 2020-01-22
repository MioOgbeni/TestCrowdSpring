package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.user.UserImp;

import java.util.List;

public interface UserService {
    void save(UserImp user);

    void delete(UserImp user);

    List<UserImp> findAll();

    UserImp findByUsername(String username);

    UserImp findByEmail(String username);

    UserImp findById(String id);
}
