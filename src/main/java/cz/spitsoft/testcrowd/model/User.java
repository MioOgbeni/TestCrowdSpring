package cz.spitsoft.testcrowd.model;

import java.util.Set;

public interface User<E> {
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

    String getImageUrl();

    void setImageUrl(String imageUrl);

    Set<E> getRoles();

    void setRoles(Set<E> roles);
}
