package cz.spitsoft.testcrowd.model.evidences;

import cz.spitsoft.testcrowd.model.BaseEntity;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "EVIDENCE_ID"))
})
@Table(name = "TBL_EVIDENCES")
public class EvidenceImp extends BaseEntity implements Evidence {
    @Column(name = "NAME")
    @Size(max = 255, message = "{evidence.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "FILE_NAME")
    @Size(max = 255, message = "{evidence.fileName.invalid}")
    @NotEmpty
    private String fileName;

    @Column(name = "EXTENSION")
    @NotEmpty
    private String extension;

    @Column(name = "ATTACHED_ON")
    @NotEmpty
    private Date attachedOn;

    public EvidenceImp() {
        super();
    }

    public EvidenceImp(String name, String fileName, String extension, Date attachedOn) {
        super();
        this.name = name;
        this.fileName = fileName;
        this.extension = extension;
        this.attachedOn = attachedOn;
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
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    @Override
    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public Date getAttachedOn() {
        return attachedOn;
    }

    @Override
    public void setAttachedOn(Date attachedOn) {
        this.attachedOn = attachedOn;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("name", this.getName()).toString();
    }
}
