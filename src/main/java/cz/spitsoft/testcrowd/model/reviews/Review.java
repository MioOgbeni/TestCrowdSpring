package cz.spitsoft.testcrowd.model.reviews;

import java.util.Date;

public interface Review<U> {
    String getContent();

    void setContent(String content);

    int getRating();

    void setRating(int rating);

    Date getCreatedOn();

    void setCreatedOn(Date createdOn);

    U getCreatedBy();

    void setCreatedBy(U createdBy);

}
