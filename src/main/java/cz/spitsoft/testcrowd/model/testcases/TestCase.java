package cz.spitsoft.testcrowd.model.testcases;

import java.util.Date;
import java.util.List;

public interface TestCase<C, T, E, U, R> {
    String getName();

    void setName(String name);

    int getSkillDifficulty();

    void setSkillDifficulty(int skillDifficulty);

    int getTimeDifficulty();

    void setTimeDifficulty(int timeDifficulty);

    C getTestCategory();

    void setTestCategory(C testCategory);

    T getSoftwareType();

    void setSoftwareType(T softwareType);

    String getDescription();

    void setDescription(String description);

    int getReward();

    void setReward(int reward);

    List<E> getEvidences();

    void setEvidences(List<E> evidences);

    U getCreatedBy();

    void setCreatedBy(U createdBy);

    Date getCreatedOn();

    void setCreatedOn(Date createdOn);

    Date getAvailableTo();

    void setAvailableTo(Date availableTo);

    List<R> getReviews();

    void setReviews(List<R> reviews);

    int getRating();

    void setRating(int rating);

    void countRating();

    boolean isInGroup();

    void setInGroup(boolean inGroup);
}
