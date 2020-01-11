package cz.spitsoft.testcrowd.model;

import java.util.Date;

public interface Role<T> {
    RoleType getName();

    void setName(RoleType roleType);

    String getDescription();

    void setDescription(String description);

    Date getCreatedOn();

    void setCreatedOn(Date createdOn);

    Date getModifiedOn();

    void setModifiedOn(Date modifiedOn);

    User<T> getModifiedBy();

    void setModifiedBy(User<T> modifiedBy);
}
