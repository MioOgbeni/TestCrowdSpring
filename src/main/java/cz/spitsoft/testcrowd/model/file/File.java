package cz.spitsoft.testcrowd.model.file;

import java.util.Date;

public interface File {

    String getFileName();

    void setFileName(String fileName);

    byte[] getData();

    void setData(byte[] data);

    String getContentType();

    void setContentType(String contentType);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

}
