package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.file.FileImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;
import org.hibernate.validator.constraints.Range;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TEST_RESULT_ID"))
})
@Table(name = "TBL_TEST_RESULTS")
public class TestResultImp extends BaseEntity implements TestResult {

    @Column(name = "DESCRIPTION")
    @Size(max = 240, message = "{testResult.description.invalid}")
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "TEST_CASE")
    @Target(TestCaseImp.class)
    @NotNull
    private TestCaseImp testCase;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "TEST_RESULT_USER")
    @Target(UserImp.class)
    @NotNull
    private UserImp user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = FileImp.class)
    private List<FileImp> files;

    @Column(name = "TEST_RESULT_STATUS")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TestResultStatus testResultStatus;

    @Column(name = "REWARD")
    @Range(min = 1, max = 1000000, message = "{testResult.reward.invalid}")
    private int reward;

    @Column(name = "TAKEN_AT")
    @NotNull
    private Date takenAt;

    @Column(name = "FINISHED_AT")
    private Date finishedAt;

    public TestResultImp() {
        super();
    }

    public TestResultImp(String description, TestCaseImp testCase, UserImp user, List<FileImp> files, TestResultStatus testResultStatus, int reward, Date takenAt, Date finishedAt) {
        super();
        this.description = description;
        this.testCase = testCase;
        this.user = user;
        this.files = files;
        this.testResultStatus = testResultStatus;
        this.reward = reward;
        this.takenAt = takenAt;
        this.finishedAt = finishedAt;
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
    public TestCaseImp getTestCase() {
        return testCase;
    }

    @Override
    public void setTestCase(TestCaseImp testCase) {
        this.testCase = testCase;
    }

    @Override
    public UserImp getUser() {
        return user;
    }

    @Override
    public void setUser(UserImp user) {
        this.user = user;
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
    public TestResultStatus getTestResultStatus() {
        return testResultStatus;
    }

    @Override
    public void setTestResultStatus(TestResultStatus testResultStatus) {
        this.testResultStatus = testResultStatus;
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
    public Date getTakenAt() {
        return takenAt;
    }

    @Override
    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    @Override
    public Date getFinishedAt() {
        return finishedAt;
    }

    @Override
    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("takenAt", this.getTakenAt())
                .toString();
    }

}
