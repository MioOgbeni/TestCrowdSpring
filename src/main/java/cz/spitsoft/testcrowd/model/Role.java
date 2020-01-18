package cz.spitsoft.testcrowd.model;

import java.util.Date;

public interface Role {
    RoleType getName();

    void setName(RoleType roleType);

    String getDescription();

    void setDescription(String description);

    Date getCreatedOn();

    void setCreatedOn(Date createdOn);
}
