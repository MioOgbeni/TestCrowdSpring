package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.user.UserImp;

import java.util.Date;
import java.util.List;

public interface TestGroup {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    int getSkillDifficulty();

    void setSkillDifficulty(int skillDifficulty);

    int getTimeDifficulty();

    void setTimeDifficulty(int timeDifficulty);

    List<TestCaseImp> getTestCases();

    void setTestCases(List<TestCaseImp> testCases);

    double getRewardMultiplier();

    void setRewardMultiplier(double rewardMultiplier);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    UserImp getCreatedBy();

    void setCreatedBy(UserImp createdBy);

    Date getAvailableTo();

    void setAvailableTo(Date availableTo);

    int getRating();

    void setRating(int rating);

}
