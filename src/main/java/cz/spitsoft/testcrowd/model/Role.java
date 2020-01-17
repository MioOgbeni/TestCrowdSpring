package cz.spitsoft.testcrowd.model;

import java.util.Date;

public interface Role<U> {
    RoleType getName();

    void setName(RoleType roleType);

    String getDescription();

    void setDescription(String description);

    Date getCreatedOn();

    void setCreatedOn(Date createdOn);

    Date getModifiedOn();

    void setModifiedOn(Date modifiedOn);

    U getModifiedBy();

    void setModifiedBy(U modifiedBy);
}
