package cz.spitsoft.testcrowd.model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "USER_ID"))
})
@Table(name = "TBL_USERS")
public class UserImp<E> extends BaseEntity implements User<E> {
    @Column(name = "FIRSTNAME")
    @Size(max = 30, message = "{user.firstName.invalid}")
    private String firstName;

    @Column(name = "LASTNAME")
    @Size(max = 30, message = "{user.lastName.invalid}")
    private String lastName;

    @Column(name = "USERNAME")
    @Size(max = 30, min = 1, message = "{user.username.invalid}")
    @NotEmpty
    private String username;

    @Column(name = "PASSWORD")
    @Size(max = 255, min = 1, message = "{user.password.invalid}")
    @NotEmpty
    private String password;

    @Column(name = "EMAIL")
    @Size(max = 255, min = 1, message = "{user.email.invalid}")
    @NotEmpty
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleImp.class)
    @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @NotEmpty
    private Set<E> roles;

    public UserImp() {
        super();
    }

    public UserImp(String firstName, String lastName, String username, String password, String email, Set<E> roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
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
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
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
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Set<E> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(Set<E> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew()).append("name", this.getUsername()).toString();
    }
}
