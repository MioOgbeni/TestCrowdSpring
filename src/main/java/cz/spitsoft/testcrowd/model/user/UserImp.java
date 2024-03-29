package cz.spitsoft.testcrowd.model.user;

import cz.spitsoft.testcrowd.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "USER_ID"))
})
@Table(name = "TBL_USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "EMAIL"),
        @UniqueConstraint(columnNames = "USERNAME"),
})
public class UserImp extends BaseEntity implements User {

    @Column(name = "ROLE_TYPE")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "USERNAME")
    @Size(max = 40, min = 4, message = "{user.username.invalid}")
    @NotEmpty
    private String username;

    @Email
    @Column(name = "EMAIL")
    @Size(max = 160, min = 4, message = "{user.email.invalid}")
    @NotEmpty
    private String email;

    @Column(name = "PASSWORD")
    @NotEmpty
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "FIRST_NAME")
    @Size(max = 80, message = "{user.firstName.invalid}")
    private String firstName;

    @Column(name = "LAST_NAME")
    @Size(max = 80, message = "{user.lastName.invalid}")
    private String lastName;

    @Column(name = "ACCOUNT_BALANCE")
    @NotNull
    private int accountBalance = 0;

    public UserImp() {
        super();
    }

    public UserImp(RoleType roleType, String username, String email, String password, String passwordConfirm, String firstName, String lastName, int accountBalance) {
        super();
        this.roleType = roleType;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountBalance = accountBalance;
    }

    @Override
    public RoleType getRoleType() {
        return roleType;
    }

    @Override
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    @Override
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int getAccountBalance() {
        return accountBalance;
    }

    @Override
    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "UserImp{" +
                "roleType=" + roleType +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserImp userImp = (UserImp) o;
        return accountBalance == userImp.accountBalance &&
                roleType == userImp.roleType &&
                Objects.equals(username, userImp.username) &&
                Objects.equals(email, userImp.email) &&
                Objects.equals(password, userImp.password) &&
                Objects.equals(passwordConfirm, userImp.passwordConfirm) &&
                Objects.equals(firstName, userImp.firstName) &&
                Objects.equals(lastName, userImp.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleType, username, email, password, passwordConfirm, firstName, lastName, accountBalance);
    }
}
