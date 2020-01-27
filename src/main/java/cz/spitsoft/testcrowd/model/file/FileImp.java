package cz.spitsoft.testcrowd.model.file;

import cz.spitsoft.testcrowd.model.BaseEntity;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "FILE_ID"))
})
@Table(name = "TBL_FILES")
public class FileImp extends BaseEntity implements File {

    @Column(name = "NAME")
    @Size(max = 80, message = "{evidence.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "FILE_NAME")
    @Size(max = 240, message = "{evidence.fileName.invalid}")
    @NotEmpty
    private String fileName;

    @Column(name = "EXTENSION")
    @Size(max = 20, message = "{evidence.extension.invalid}")
    @NotEmpty
    private String extension;

    @Column(name = "CREATED_AT")
    @NotNull
    private Date createdAt;

    public FileImp() {
        super();
    }

    public FileImp(String name, String fileName, String extension, Date createdAt) {
        super();
        this.name = name;
        this.fileName = fileName;
        this.extension = extension;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("name", this.getName())
                .toString();
    }

}
