package cz.spitsoft.testcrowd.model.testcases;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.UserImp;
import org.hibernate.annotations.Target;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TEST_CATEGORY_ID"))
})
@Table(name = "TBL_TEST_CATEGORY")
public class TestCategoryImp<U> extends BaseEntity implements TestCategory<U> {
    @Column(name = "NAME")
    @Size(max = 30, message = "{testCategory.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 255, message = "{testCategory.description.invalid}")
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

    public TestCategoryImp() {
        super();
    }

    public TestCategoryImp(String name, String description, Boolean valid, Date createdOn, Date modifiedOn, U modifiedBy) {
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
