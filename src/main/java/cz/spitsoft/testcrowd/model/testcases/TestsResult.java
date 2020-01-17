package cz.spitsoft.testcrowd.model.testcases;

import java.util.Date;
import java.util.List;

public interface TestsResult<T, U, E> {
    T getTestCase();

    void setTestCase(T testCase);

    U getUser();

    void setUser(U user);

    List<E> getEvidence();

    void setEvidence(List<E> evidence);

    TestStatus getTestStatus();

    void setTestStatus(TestStatus testStatus);

    TestsResultStatus getTestsResultStatus();

    void setTestsResultStatus(TestsResultStatus testsResultStatus);

    Date getTakenOn();

    void setTakenOn(Date takenOn);

    Date getFinishedOn();

    void setFinishedOn(Date finishedOn);
}
