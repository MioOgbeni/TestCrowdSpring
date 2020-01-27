package cz.spitsoft.testcrowd.model.file;

import java.util.Date;

public interface File {

    String getName();

    void setName(String name);

    String getFileName();

    void setFileName(String fileName);

    String getExtension();

    void setExtension(String extension);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

}
