package cz.spitsoft.testcrowd.model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "ROLE_ID"))
})
@Table(name = "TBL_ROLES")
public class RoleImp extends BaseEntity implements Role {

    @Column(name = "NAME")
    @Size(max = 20, min = 3, message = "{testcase.name.invalid}")
    @NotEmpty
    private RoleType name;

    @Column(name = "DESCRIPTION")
    @Size(max = 255, message = "{testcase.name.invalid}")
    private String description;

    @Column(name = "CREATED_ON")
    private Long createdOn;

    @Column(name = "MODIFIED_ON")
    private Long modifiedOn;

    public RoleImp() {
        super();
    }

    public RoleImp(RoleType roleType, String description, Long createdOn, Long modifiedOn) {
        super();
        this.name = roleType;
        this.description = description;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    @Override
    public RoleType getName() {
        return name;
    }

    @Override
    public void setName(RoleType roleType) {
        this.name = roleType;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Long getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public Long getModifiedOn() {
        return modifiedOn;
    }

    @Override
    public void setModifiedOn(Long modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew()).append("name", this.getName()).toString();
    }
}
