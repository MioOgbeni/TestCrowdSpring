package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TEST_GROUP_RESULT_ID"))
})
@Table(name = "TBL_TEST_GROUP_RESULTS")
public class TestGroupResultImp extends BaseEntity implements TestGroupResult {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_GROUP")
    @Target(TestGroupImp.class)
    @NotNull
    private TestGroupImp testGroup;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_GROUP_RESULT_USER")
    @Target(UserImp.class)
    @NotNull
    private UserImp user;

    @Column(name = "TEST_GROUP_RESULT_STATUS")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TestGroupResultStatus testGroupResultStatus;

    @Column(name = "CREATED_AT")
    @NotNull
    private Date takenAt;

    @Column(name = "FINISHED_AT")
    private Date finishedAt;

    public TestGroupResultImp() {
        super();
    }

    public TestGroupResultImp(TestGroupImp testGroup, UserImp user, TestGroupResultStatus testGroupResultStatus, Date takenAt, Date finishedAt) {
        super();
        this.testGroup = testGroup;
        this.user = user;
        this.testGroupResultStatus = testGroupResultStatus;
        this.takenAt = takenAt;
        this.finishedAt = finishedAt;
    }

    @Override
    public TestGroupImp getTestGroup() {
        return testGroup;
    }

    @Override
    public void setTestGroup(TestGroupImp testGroup) {
        this.testGroup = testGroup;
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
    public TestGroupResultStatus getTestGroupResultStatus() {
        return testGroupResultStatus;
    }

    @Override
    public void setTestGroupResultStatus(TestGroupResultStatus testGroupResultStatus) {
        this.testGroupResultStatus = testGroupResultStatus;
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

}
