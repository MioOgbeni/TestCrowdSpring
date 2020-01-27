package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.file.FileImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TEST_RESULT_ID"))
})
@Table(name = "TBL_TEST_RESULTS")
public class TestResultImp extends BaseEntity implements TestResult {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_CASE")
    @Target(TestCaseImp.class)
    @NotNull
    private TestCaseImp testCase;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_RESULT_USER")
    @Target(UserImp.class)
    @NotNull
    private UserImp user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = FileImp.class)
    @NotNull
    private List<FileImp> file;

    @Column(name = "TEST_RESULT_STATUS")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TestResultStatus testResultStatus;

    @Column(name = "TAKEN_AT")
    @NotNull
    private Date takenAt;

    @Column(name = "FINISHED_AT")
    private Date finishedAt;

    public TestResultImp() {
        super();
    }

    public TestResultImp(TestCaseImp testCase, UserImp user, List<FileImp> file, TestResultStatus testResultStatus, Date takenAt, Date finishedAt) {
        super();
        this.testCase = testCase;
        this.user = user;
        this.file = file;
        this.testResultStatus = testResultStatus;
        this.takenAt = takenAt;
        this.finishedAt = finishedAt;
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
    public List<FileImp> getFile() {
        return file;
    }

    @Override
    public void setFile(List<FileImp> file) {
        this.file = file;
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
