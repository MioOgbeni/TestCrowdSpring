package cz.spitsoft.testcrowd.model;

public interface Role {
    RoleType name = null;
    String description = null;
    Long createdOn = null;
    Long modifiedOn = null;

    RoleType getName();

    void setName(RoleType roleType);

    String getDescription();

    void setDescription(String description);

    Long getCreatedOn();

    void setCreatedOn(Long createdOn);

    Long getModifiedOn();

    void setModifiedOn(Long modifiedOn);
}
