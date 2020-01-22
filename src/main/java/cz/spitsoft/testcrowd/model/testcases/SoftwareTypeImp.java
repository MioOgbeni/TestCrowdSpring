package cz.spitsoft.testcrowd.model.testcases;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "SW_TYPE_ID"))
})
@Table(name = "TBL_SW_TYPES")
public class SoftwareTypeImp<U> extends BaseEntity implements SoftwareType<U> {
    @Column(name = "NAME")
    @Size(max = 30, message = "{softwareType.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 255, message = "{softwareType.description.invalid}")
    private String description;

    @Column(name = "VALID")
    @NotEmpty
    private Boolean valid;

    @Column(name = "CREATED_ON")
    @NotEmpty
    private Date createdOn;

    @Column(name = "MODIFIED_ON")
    @NotEmpty
    private Date modifiedOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MODIFIED_BY")
    @Target(UserImp.class)
    @NotEmpty
    private U modifiedBy;

    public SoftwareTypeImp() {
        super();
    }

    public SoftwareTypeImp(String name, String description, Boolean valid, Date createdOn, Date modifiedOn, U modifiedBy) {
        super();
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public Boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(Boolean valid) {
        this.valid = valid;
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
    public U getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public void setModifiedBy(U modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("name", this.getName()).toString();
    }
}
