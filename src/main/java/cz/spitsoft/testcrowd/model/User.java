package cz.spitsoft.testcrowd.model;

import java.util.Set;

public interface User {
    String firstName = null;
    String lastName = null;
    String username = null;
    String password = null;
    String email = null;
    Set<RoleImp> roles = null;

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    Set<RoleImp> getRoles();

    void setRoles(Set<RoleImp> roles);
}
