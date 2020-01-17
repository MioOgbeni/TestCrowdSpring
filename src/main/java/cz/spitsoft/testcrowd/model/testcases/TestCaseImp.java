package cz.spitsoft.testcrowd.model.testcases;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.UserImp;
import cz.spitsoft.testcrowd.model.evidences.EvidenceImp;
import cz.spitsoft.testcrowd.model.reviews.ReviewImp;
import org.hibernate.annotations.Target;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TESTCASE_ID"))
})
@Table(name = "TBL_TESTCASES")
public class TestCaseImp<C, T, E, U, R> extends BaseEntity implements TestCase<C, T, E, U, R> {

    @Column(name = "NAME")
    @Size(max = 20, min = 3, message = "{testcase.name.invalid}")
    @NotEmpty
    private String name;

    @Column(name = "SKILL_DIFFICULTY")
    @Size(max = 5, min = 1, message = "{testcase.skillDifficulty.invalid}")
    @NotEmpty
    private int skillDifficulty;

    @Column(name = "TIME_DIFFICULTY")
    @Size(max = 5, min = 1, message = "{testcase.timeDifficulty.invalid}")
    @NotEmpty
    private int timeDifficulty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_CATEGORY")
    @Target(TestCategoryImp.class)
    @NotEmpty
    private C testCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SW_TYPE")
    @Target(SoftwareTypeImp.class)
    @NotEmpty
    private T softwareType;

    @Column(name = "DESCRIPTION")
    @Size(max = 255, message = "{testcase.description.invalid}")
    private String description;

    @Column(name = "REWARD")
    @Size(min = 1, message = "{testcase.reward.invalid}")
    @NotEmpty
    private int reward;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = EvidenceImp.class)
    @NotEmpty
    private List<E> evidences;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ReviewImp.class)
    @NotEmpty
    private List<R> reviews;

    @Column(name = "RATING")
    @NotEmpty
    private int rating;

    @Column(name = "IN_GROUP")
    @NotEmpty
    private boolean inGroup;

    public TestCaseImp() {
        super();
    }

    public TestCaseImp(String name, int skillDifficulty, int timeDifficulty, C testCategory, T softwareType, String description, int reward, List<E> evidences, U createdBy, Date createdOn, Date availableTo, List<R> reviews, int rating, boolean inGroup) {
        super();
        this.name = name;
        this.skillDifficulty = skillDifficulty;
        this.timeDifficulty = timeDifficulty;
        this.testCategory = testCategory;
        this.softwareType = softwareType;
        this.description = description;
        this.reward = reward;
        this.evidences = evidences;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.availableTo = availableTo;
        this.reviews = reviews;
        this.rating = rating;
        this.inGroup = inGroup;
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
    public int getTimeDifficulty() {
        return timeDifficulty;
    }

    @Override
    public void setTimeDifficulty(int timeDifficulty) {
        this.timeDifficulty = timeDifficulty;
    }

    @Override
    public C getTestCategory() {
        return testCategory;
    }

    @Override
    public void setTestCategory(C testCategory) {
        this.testCategory = testCategory;
    }

    @Override
    public T getSoftwareType() {
        return softwareType;
    }

    @Override
    public void setSoftwareType(T softwareType) {
        this.softwareType = softwareType;
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
    public int getReward() {
        return reward;
    }

    @Override
    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public List<E> getEvidences() {
        return evidences;
    }

    @Override
    public void setEvidences(List<E> evidences) {
        this.evidences = evidences;
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
    public List<R> getReviews() {
        return reviews;
    }

    @Override
    public void setReviews(List<R> reviews) {
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
    public void countRating() {
        if (reviews.size() == 0) {
            rating = 0;
        } else {
            int reviewRating = 0;

            for (R review : reviews) reviewRating += ((ReviewImp<U>) review).getRating();

            rating = reviewRating / reviews.size();
        }
    }

    @Override
    public boolean isInGroup() {
        return inGroup;
    }

    @Override
    public void setInGroup(boolean inGroup) {
        this.inGroup = inGroup;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("name", this.getName()).toString();
    }
}
