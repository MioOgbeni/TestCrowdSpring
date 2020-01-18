package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.UserImp;

public interface UserService {
    void save(UserImp user);

    UserImp findByUsername(String username);
}
