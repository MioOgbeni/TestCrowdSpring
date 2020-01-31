package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.file.FileImp;
import cz.spitsoft.testcrowd.model.review.ReviewImp;
import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;
import org.hibernate.validator.constraints.Range;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TEST_CASE_ID"))
})
@Table(name = "TBL_TEST_CASES")
public class TestCaseImp extends BaseEntity implements TestCase {

    @Column(name = "NAME")
    @Size(max = 80, message = "{testCase.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 240, message = "{testCase.description.invalid}")
    private String description;

    @Column(name = "SKILL_DIFFICULTY")
    @Range(min = 1, max = 5, message = "{testCase.skillDifficulty.invalid}")
    @NotNull
    private int skillDifficulty;

    @Column(name = "TIME_DIFFICULTY")
    @Range(min = 1, max = 5, message = "{testCase.timeDifficulty.invalid}")
    @NotNull
    private int timeDifficulty;

    @Column(name = "TEST_STATUS")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TestStatus testStatus;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "TEST_CATEGORY")
    @Target(TestCategoryImp.class)
    private TestCategoryImp testCategory;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "SOFTWARE_TYPE")
    @Target(SoftwareTypeImp.class)
    private SoftwareTypeImp softwareType;

    @Column(name = "REWARD")
    @Range(min = 1, max = 1000000, message = "{testCase.reward.invalid}")
    private int reward;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = FileImp.class)
    private List<FileImp> files;

    @Column(name = "CREATED_AT")
    @NotNull
    private Date createdAt;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "CREATED_BY")
    @Target(UserImp.class)
    @NotNull
    private UserImp createdBy;

    @Column(name = "AVAILABLE_TO")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date availableTo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ReviewImp.class)
    private List<ReviewImp> reviews;

    @Column(name = "RATING")
    private int rating;

    public TestCaseImp() {
        super();
    }

    public TestCaseImp(String name, String description, int skillDifficulty, int timeDifficulty, TestStatus testStatus, TestCategoryImp testCategory, SoftwareTypeImp softwareType, int reward, List<FileImp> files, Date createdAt, UserImp createdBy, Date availableTo, List<ReviewImp> reviews, int rating) {
        super();
        this.name = name;
        this.description = description;
        this.skillDifficulty = skillDifficulty;
        this.timeDifficulty = timeDifficulty;
        this.testStatus = testStatus;
        this.testCategory = testCategory;
        this.softwareType = softwareType;
        this.reward = reward;
        this.files = files;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.availableTo = availableTo;
        this.reviews = reviews;
        this.rating = rating;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getSkillDifficulty() {
        return skillDifficulty;
    }

    @Override
    public void setSkillDifficulty(int skillDifficulty) {
        this.skillDifficulty = skillDifficulty;
    }

    @Override
    public int getTimeDifficulty() {
        return timeDifficulty;
    }

    @Override
    public void setTimeDifficulty(int timeDifficulty) {
        this.timeDifficulty = timeDifficulty;
    }

    @Override
    public TestStatus getTestStatus() {
        return testStatus;
    }

    @Override
    public void setTestStatus(TestStatus testStatus) {
        this.testStatus = testStatus;
    }

    @Override
    public TestCategoryImp getTestCategory() {
        return testCategory;
    }

    @Override
    public void setTestCategory(TestCategoryImp testCategory) {
        this.testCategory = testCategory;
    }

    @Override
    public SoftwareTypeImp getSoftwareType() {
        return softwareType;
    }

    @Override
    public void setSoftwareType(SoftwareTypeImp softwareType) {
        this.softwareType = softwareType;
    }

    @Override
    public int getReward() {
        return reward;
    }

    @Override
    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public List<FileImp> getFiles() {
        return files;
    }

    @Override
    public void setFiles(List<FileImp> files) {
        this.files = files;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public UserImp getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(UserImp createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getAvailableTo() {
        return availableTo;
    }

    @Override
    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    @Override
    public List<ReviewImp> getReviews() {
        return reviews;
    }

    @Override
    public void setReviews(List<ReviewImp> reviews) {
        this.reviews = reviews;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("name", this.getName())
                .toString();
    }

}
