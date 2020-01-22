package cz.spitsoft.testcrowd.model.user;

public interface User/*<R>*/ {

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getPasswordConfirm();

    void setPasswordConfirm(String passwordConfirm);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    int getAccountBalance();

    void setAccountBalance(int accountBalance);

    RoleType getRoleType();

    void setRoleType(RoleType roleType);

    /*Set<R> getRoles();

    void setRoles(Set<R> roles);*/
}
