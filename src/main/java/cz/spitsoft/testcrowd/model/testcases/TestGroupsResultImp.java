package cz.spitsoft.testcrowd.model.testcases;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TEST_GROUPS_RESULT_ID"))
})
@Table(name = "TBL_TEST_GROUPS_RESULTS")
public class TestGroupsResultImp<G, U> extends BaseEntity implements TestGroupsResult<G, U> {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_GROUP")
    @Target(TestGroupImp.class)
    @NotEmpty
    private G testGroup;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_GROUPS_USER")
    @Target(UserImp.class)
    @NotEmpty
    private U user;

    @Column(name = "TEST_GROUPS_RESULT_STATUS")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private TestGroupsResultStatus testGroupsResultStatus;

    @Column(name = "CREATED_ON")
    @NotEmpty
    private Date takenOn;

    @Column(name = "FINISHED_ON")
    @NotEmpty
    private Date finishedOn;

    public TestGroupsResultImp() {
        super();
    }

    public TestGroupsResultImp(G testGroup, U user, TestGroupsResultStatus testGroupsResultStatus, Date takenOn, Date finishedOn) {
        super();
        this.testGroup = testGroup;
        this.user = user;
        this.testGroupsResultStatus = testGroupsResultStatus;
        this.takenOn = takenOn;
        this.finishedOn = finishedOn;
    }

    @Override
    public G getTestGroup() {
        return testGroup;
    }

    @Override
    public void setTestGroup(G testGroup) {
        this.testGroup = testGroup;
    }

    @Override
    public U getUser() {
        return user;
    }

    @Override
    public void setUser(U user) {
        this.user = user;
    }

    @Override
    public TestGroupsResultStatus getTestGroupsResultStatus() {
        return testGroupsResultStatus;
    }

    @Override
    public void setTestGroupsResultStatus(TestGroupsResultStatus testGroupsResultStatus) {
        this.testGroupsResultStatus = testGroupsResultStatus;
    }

    @Override
    public Date getTakenOn() {
        return takenOn;
    }

    @Override
    public void setTakenOn(Date takenOn) {
        this.takenOn = takenOn;
    }

    @Override
    public Date getFinishedOn() {
        return finishedOn;
    }

    @Override
    public void setFinishedOn(Date finishedOn) {
        this.finishedOn = finishedOn;
    }
}
