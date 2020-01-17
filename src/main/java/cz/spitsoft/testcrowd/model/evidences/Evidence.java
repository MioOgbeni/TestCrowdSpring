package cz.spitsoft.testcrowd.model.evidences;

import java.util.Date;

public interface Evidence {
    String getName();

    void setName(String name);

    String getFileName();

    void setFileName(String fileName);

    String getExtension();

    void setExtension(String extension);

    Date getAttachedOn();

    void setAttachedOn(Date attachedOn);
}
