package cz.spitsoft.testcrowd.model.testcases;

import cz.spitsoft.testcrowd.model.User;

import java.util.Date;

public interface SoftwareType<T> {
    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Boolean isValid();

    void setValid(Boolean valid);

    Date getCreatedOn();

    void setCreatedOn(Date createdOn);

    Date getModifiedOn();

    void setModifiedOn(Date modifiedOn);

    User<T> getModifiedBy();

    void setModifiedBy(User<T> modifiedBy);
}
