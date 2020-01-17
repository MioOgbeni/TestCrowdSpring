package cz.spitsoft.testcrowd.model.testcases;

import java.util.Date;
import java.util.List;

public interface TestGroup<T, U> {
    String getName();

    void setName(String name);

    int getSkillDifficulty();

    void setSkillDifficulty(int skillDifficulty);

    void countSkillDifficulty();

    int getTimeDifficulty();

    void setTimeDifficulty(int timeDifficulty);

    void countTimeDifficulty();

    List<T> getTestCases();

    void setTestCases(List<T> testCases);

    double getRewardMultiplier();

    void setRewardMultiplier(double rewardMultiplier);

    U getCreatedBy();

    void setCreatedBy(U createdBy);

    Date getCreatedOn();

    void setCreatedOn(Date createdOn);

    Date getAvailableTo();

    void setAvailableTo(Date availableTo);

    int getRating();

    void setRating(int rating);

    void countRating();
}
