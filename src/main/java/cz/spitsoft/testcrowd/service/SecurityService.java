package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.RoleImp;
import cz.spitsoft.testcrowd.model.UserImp;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

    UserImp<RoleImp> getCurrentUser();

    boolean isCurrentUserById(String id);

    boolean isCurrentUserAdmin();
}
