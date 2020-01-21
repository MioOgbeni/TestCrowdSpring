package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.RoleImp;
import cz.spitsoft.testcrowd.model.UserImp;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserService {
    void save(UserImp user, Set<RoleImp> roles);

    UserImp findByUsername(String username);

    UserImp findByEmail(String username);

    UserImp findById(String id);
}
