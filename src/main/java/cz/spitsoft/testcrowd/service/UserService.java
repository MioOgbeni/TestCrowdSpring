package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.RoleImp;
import cz.spitsoft.testcrowd.model.UserImp;

import java.util.Set;

public interface UserService {
    void save(UserImp user, Set<RoleImp> roles);

    void save(UserImp user);

    UserImp findByUsername(String username);

    UserImp findByEmail(String username);

    UserImp findById(String id);
}
