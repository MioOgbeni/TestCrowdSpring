package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;
import org.hibernate.validator.constraints.Range;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TEST_GROUP_ID"))
})
@Table(name = "TBL_TEST_GROUPS")
public class TestGroupImp extends BaseEntity implements TestGroup {

    @Column(name = "NAME")
    @Size(max = 80, message = "{testGroup.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 240, message = "{testGroup.description.invalid}")
    private String description;

    @Column(name = "SKILL_DIFFICULTY")
    @Range(min = 1, max = 5, message = "{testGroup.skillDifficulty.invalid}")
    @NotNull
    private int skillDifficulty;

    @Column(name = "TIME_DIFFICULTY")
    @Range(min = 1, max = 5, message = "{testGroup.timeDifficulty.invalid}")
    @NotNull
    private int timeDifficulty;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = TestCaseImp.class)
    @NotEmpty
    private List<TestCaseImp> testCases;

    @Column(name = "REWARD_MULTIPLIER")
    @NotEmpty
    private double rewardMultiplier;

    @Column(name = "CREATED_AT")
    @NotNull
    private Date createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREATED_BY")
    @Target(UserImp.class)
    @NotNull
    private UserImp createdBy;

    @Column(name = "AVAILABLE_TO")
    private Date availableTo;

    @Column(name = "RATING")
    @NotEmpty
    private int rating;

    public TestGroupImp() {
        super();
    }

    public TestGroupImp(String name, String description, int skillDifficulty, int timeDifficulty, List<TestCaseImp> testCases, double rewardMultiplier, Date createdAt, UserImp createdBy, Date availableTo, int rating) {
        super();
        this.name = name;
        this.description = description;
        this.skillDifficulty = skillDifficulty;
        this.timeDifficulty = timeDifficulty;
        this.testCases = testCases;
        this.rewardMultiplier = rewardMultiplier;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.availableTo = availableTo;
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
    public List<TestCaseImp> getTestCases() {
        return testCases;
    }

    @Override
    public void setTestCases(List<TestCaseImp> testCases) {
        this.testCases = testCases;
    }

    @Override
    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    @Override
    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
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
