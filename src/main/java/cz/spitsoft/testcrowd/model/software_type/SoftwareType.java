package cz.spitsoft.testcrowd.model.software_type;

import cz.spitsoft.testcrowd.model.user.UserImp;

import java.util.Date;

public interface SoftwareType {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Boolean getEnabled();

    void setEnabled(Boolean valid);

    Date getUpdatedAt();

    void setUpdatedAt(Date updatedAt);

    UserImp getUpdatedBy();

    void setUpdatedBy(UserImp updatedBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    UserImp getCreatedBy();

    void setCreatedBy(UserImp cratedBy);

}
