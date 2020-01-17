package cz.spitsoft.testcrowd.model.testcases;

import java.util.Date;

public interface TestCategory<U> {
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

    U getModifiedBy();

    void setModifiedBy(U modifiedBy);
}
