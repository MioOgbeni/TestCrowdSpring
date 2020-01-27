package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.file.FileImp;
import cz.spitsoft.testcrowd.model.review.ReviewImp;
import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import cz.spitsoft.testcrowd.model.user.UserImp;

import java.util.Date;
import java.util.List;

public interface TestCase {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    int getSkillDifficulty();

    void setSkillDifficulty(int skillDifficulty);

    int getTimeDifficulty();

    void setTimeDifficulty(int timeDifficulty);

    TestStatus getTestStatus();

    void setTestStatus(TestStatus testStatus);

    TestCategoryImp getTestCategory();

    void setTestCategory(TestCategoryImp testCategory);

    SoftwareTypeImp getSoftwareType();

    void setSoftwareType(SoftwareTypeImp softwareType);

    int getReward();

    void setReward(int reward);

    List<FileImp> getFiles();

    void setFiles(List<FileImp> evidences);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    UserImp getCreatedBy();

    void setCreatedBy(UserImp createdBy);

    Date getAvailableTo();

    void setAvailableTo(Date availableTo);

    List<ReviewImp> getReviews();

    void setReviews(List<ReviewImp> reviews);

    int getRating();

    void setRating(int rating);

    boolean isInGroup();

    void setInGroup(boolean inGroup);

}
