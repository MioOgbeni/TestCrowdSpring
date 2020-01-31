package cz.spitsoft.testcrowd.model.file;

import java.util.Date;

public interface File {

    String getFileName();

    void setFileName(String fileName);

    String getFilePath();

    void setFilePath(String filePath);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

}
