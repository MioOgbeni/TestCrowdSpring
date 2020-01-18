package cz.spitsoft.testcrowd.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
