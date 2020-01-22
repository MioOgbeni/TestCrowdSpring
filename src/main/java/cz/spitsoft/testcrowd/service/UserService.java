package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.user.UserImp;

public interface UserService {
    void save(UserImp user);

    void delete(UserImp user);

    UserImp findByUsername(String username);

    UserImp findByEmail(String username);

    UserImp findById(String id);
}
