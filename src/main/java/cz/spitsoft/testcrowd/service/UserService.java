package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.RoleImp;
import cz.spitsoft.testcrowd.model.UserImp;

import java.util.Set;

public interface UserService {
    void save(UserImp<RoleImp> user, Set<RoleImp> roles);

    void save(UserImp<RoleImp> user);

    void delete(UserImp<RoleImp> user);

    UserImp<RoleImp> findByUsername(String username);

    UserImp<RoleImp> findByEmail(String username);

    UserImp<RoleImp> findById(String id);
}
