package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.user.UserImp;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

    UserImp getCurrentUser();

    boolean isCurrentUserById(String id);

    boolean isCurrentUserAdmin();

    boolean isCurrentUserReporter();

    boolean isCurrentUserTester();

}
