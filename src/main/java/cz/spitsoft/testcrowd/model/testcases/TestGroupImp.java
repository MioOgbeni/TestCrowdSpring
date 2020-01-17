package cz.spitsoft.testcrowd.model.testcases;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.UserImp;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TEST_GROUP_ID"))
})
@Table(name = "TBL_TEST_GROUPS")
public class TestGroupImp<T, U> extends BaseEntity implements TestGroup<T, U> {
    @Column(name = "NAME")
    @Size(max = 20, min = 3, message = "{testcase.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "SKILL_DIFFICULTY")
    @Size(max = 5, message = "{testcase.skillDifficulty.invalid}")
    @NotEmpty
    private int skillDifficulty;

    @Column(name = "TIME_DIFFICULTY")
    @Size(max = 5, message = "{testcase.timeDifficulty.invalid}")
    @NotEmpty
    private int timeDifficulty;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = TestCaseImp.class)
    @NotEmpty
    private List<T> testCases;

    @Column(name = "REWARD_MULTIPLIER")
    @NotEmpty
    private double rewardMultiplier;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREATED_BY")
    @Target(UserImp.class)
    @NotEmpty
    private U createdBy;

    @Column(name = "CREATED_ON")
    @NotEmpty
    private Date createdOn;

    @Column(name = "AVAILABLE_TO")
    @NotEmpty
    private Date availableTo;

    @Column(name = "RATING")
    @NotEmpty
    private int rating;

    public TestGroupImp() {
        super();
    }

    public TestGroupImp(String name, int skillDifficulty, int timeDifficulty, List<T> testCases, double rewardMultiplier, U createdBy, Date createdOn, Date availableTo, int rating) {
        super();
        this.name = name;
        this.skillDifficulty = skillDifficulty;
        this.timeDifficulty = timeDifficulty;
        this.testCases = testCases;
        this.rewardMultiplier = rewardMultiplier;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
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
    public int getSkillDifficulty() {
        return skillDifficulty;
    }

    @Override
    public void setSkillDifficulty(int skillDifficulty) {
        this.skillDifficulty = skillDifficulty;
    }

    @Override
    public void countSkillDifficulty() {
        if (testCases.isEmpty()) {
            skillDifficulty = 0;
        } else {
            int groupSkillDifficulty = 0;

            for (T testCase : testCases) groupSkillDifficulty += ((TestCaseImp) testCase).getSkillDifficulty();

            skillDifficulty = (int) Math.ceil(groupSkillDifficulty / testCases.size());
        }
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
    public void countTimeDifficulty() {
        if (testCases.isEmpty()) {
            timeDifficulty = 0;
        } else {
            int groupTimeDifficulty = 0;

            for (T testCase : testCases) groupTimeDifficulty += ((TestCaseImp) testCase).getTimeDifficulty();

            timeDifficulty = (int) Math.ceil(groupTimeDifficulty / testCases.size());
        }
    }

    @Override
    public List<T> getTestCases() {
        return testCases;
    }

    @Override
    public void setTestCases(List<T> testCases) {
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
    public U getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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
    public void countRating() {
        if (testCases.isEmpty()) {
            rating = 0;
        } else {
            int groupRating = 0;

            for (T testCase : testCases) groupRating += ((TestCaseImp) testCase).getRating();

            rating = (int) Math.ceil(groupRating / testCases.size());
        }
    }
}
