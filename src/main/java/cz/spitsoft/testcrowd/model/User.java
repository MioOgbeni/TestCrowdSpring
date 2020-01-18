package cz.spitsoft.testcrowd.model;

import java.util.Set;

public interface User<R> {
    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getPasswordConfirm();

    void setPasswordConfirm(String passwordConfirm);

    String getEmail();

    void setEmail(String email);

    Set<R> getRoles();

    void setRoles(Set<R> roles);
}
