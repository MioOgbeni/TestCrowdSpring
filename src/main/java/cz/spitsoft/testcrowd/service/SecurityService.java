package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.RoleType;

import java.util.UUID;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

    String getCurrentUserId();

    RoleType getCurrentUserRoleType();
}
