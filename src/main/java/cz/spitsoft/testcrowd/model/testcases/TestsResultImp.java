package cz.spitsoft.testcrowd.model.testcases;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.UserImp;
import cz.spitsoft.testcrowd.model.evidences.EvidenceImp;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TESTS_RESULT_ID"))
})
@Table(name = "TBL_TESTS_RESULTS")
public class TestsResultImp<T, U, E> extends BaseEntity implements TestsResult<T, U, E> {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_CASE")
    @Target(TestCaseImp.class)
    @NotEmpty
    private T testCase;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TESTS_USER")
    @Target(UserImp.class)
    @NotEmpty
    private U user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = EvidenceImp.class)
    @NotEmpty
    private List<E> evidence;

    @Column(name = "TEST_STATUS")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private TestStatus testStatus;

    @Column(name = "TESTS_RESULT_STATUS")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private TestsResultStatus testsResultStatus;

    @Column(name = "CREATED_ON")
    @NotEmpty
    private Date takenOn;

    @Column(name = "FINISHED_ON")
    @NotEmpty
    private Date finishedOn;

    public TestsResultImp() {
        super();
    }

    public TestsResultImp(T testCase, U user, List<E> evidence, TestStatus testStatus, TestsResultStatus testsResultStatus, Date takenOn, Date finishedOn) {
        super();
        this.testCase = testCase;
        this.user = user;
        this.evidence = evidence;
        this.testStatus = testStatus;
        this.testsResultStatus = testsResultStatus;
        this.takenOn = takenOn;
        this.finishedOn = finishedOn;
    }

    @Override
    public T getTestCase() {
        return testCase;
    }

    @Override
    public void setTestCase(T testCase) {
        this.testCase = testCase;
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
    public List<E> getEvidence() {
        return evidence;
    }

    @Override
    public void setEvidence(List<E> evidence) {
        this.evidence = evidence;
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
    public TestsResultStatus getTestsResultStatus() {
        return testsResultStatus;
    }

    @Override
    public void setTestsResultStatus(TestsResultStatus testsResultStatus) {
        this.testsResultStatus = testsResultStatus;
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
