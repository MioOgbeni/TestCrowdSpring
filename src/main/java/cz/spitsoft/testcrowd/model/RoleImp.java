package cz.spitsoft.testcrowd.model;

import org.hibernate.annotations.Target;
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
public class RoleImp<T> extends BaseEntity implements Role<T> {

    @Column(name = "NAME")
    @Size(max = 20, min = 3, message = "{role.name.invalid}")
    @NotEmpty
    private RoleType name;

    @Column(name = "DESCRIPTION")
    @Size(max = 255, message = "{role.description.invalid}")
    private String description;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "MODIFIED_ON")
    private Date modifiedOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MODIFIED_BY")
    @Target(UserImp.class)
    @NotEmpty
    private User<T> modifiedBy;

    public RoleImp() {
        super();
    }

    public RoleImp(RoleType roleType, String description, Date createdOn, Date modifiedOn, User<T> modifiedBy) {
        super();
        this.name = roleType;
        this.description = description;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
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
    public Date getModifiedOn() {
        return modifiedOn;
    }

    @Override
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public User<T> getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public void setModifiedBy(User<T> modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew()).append("name", this.getName()).toString();
    }
}
