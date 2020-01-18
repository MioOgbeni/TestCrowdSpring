package cz.spitsoft.testcrowd.model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "ROLE_ID"))
})
@Table(name = "TBL_ROLES")
public class RoleImp extends BaseEntity implements Role {

    @Column(name = "NAME")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private RoleType name;

    @Column(name = "DESCRIPTION")
    @Size(max = 255, message = "{role.description.invalid}")
    private String description;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    public RoleImp() {
        super();
    }

    public RoleImp(RoleType roleType, String description, Date createdOn) {
        super();
        this.name = roleType;
        this.description = description;
        this.createdOn = createdOn;
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
    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("name", this.getName()).toString();
    }
}
