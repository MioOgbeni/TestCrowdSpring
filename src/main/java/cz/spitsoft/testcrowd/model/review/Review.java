package cz.spitsoft.testcrowd.model.review;

import cz.spitsoft.testcrowd.model.user.UserImp;

import java.util.Date;

public interface Review {

    String getContent();

    void setContent(String content);

    int getRating();

    void setRating(int rating);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    UserImp getCreatedBy();

    void setCreatedBy(UserImp createdBy);

}
