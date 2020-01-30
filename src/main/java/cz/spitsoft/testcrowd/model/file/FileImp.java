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

    @Column(name = "FILE_NAME")
    @Size(max = 240, message = "{evidence.fileName.invalid}")
    @NotEmpty
    private String fileName;

    @Lob
    @Column(name = "DTA_BLOB")
    private byte[] data;

    @Column(name = "CONTENT_TYPE")
    @Size(max = 20, message = "{evidence.contentType.invalid}")
    @NotEmpty
    private String contentType;

    @Column(name = "CREATED_AT")
    @NotNull
    private Date createdAt;

    public FileImp() {
        super();
    }

    public FileImp(String fileName, byte[] data, String contentType, Date createdAt) {
        super();
        this.fileName = fileName;
        this.data = data;
        this.contentType = contentType;
        this.createdAt = createdAt;
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
    public byte[] getData() {
        return data;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
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
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("fileName", this.getFileName())
                .toString();
    }

}
