package cz.spitsoft.testcrowd.model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_ROLE")
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

    public RoleImp(String name) {
        super();

    }

    @Override
    public RoleType getName() {
        return null;
    }

    @Override
    public void setName(RoleType roleType) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public Long getCreatedOn() {
        return null;
    }

    @Override
    public void setCreatedOn(String createdOn) {

    }

    @Override
    public Long getModifiedOn() {
        return null;
    }

    @Override
    public void setModifiedOn(String modifiedOn) {

    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew()).append("name", this.getName()).toString();
    }
}
