package cz.spitsoft.testcrowd.model.software_type;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "SOFTWARE_TYPE_ID"))
})
@Table(name = "TBL_SOFTWARE_TYPES")
public class SoftwareTypeImp extends BaseEntity implements SoftwareType {

    @Column(name = "NAME")
    @Size(max = 80, message = "{softwareType.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 240, message = "{softwareType.description.invalid}")
    private String description;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "UPDATED_AT")
    @NotNull
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UPDATED_BY")
    @Target(UserImp.class)
    @NotNull
    private UserImp updatedBy;

    @Column(name = "CREATED_AT")
    @NotNull
    private Date createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREATED_BY")
    @Target(UserImp.class)
    @NotNull
    private UserImp createdBy;

    public SoftwareTypeImp() {
        super();
    }

    public SoftwareTypeImp(String name, String description, Boolean enabled, Date updatedAt, UserImp updatedBy, Date createdAt, UserImp createdBy) {
        super();
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
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
    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public UserImp getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(UserImp updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public UserImp getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(UserImp createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("name", this.getName())
                .toString();
    }

}
