package cz.spitsoft.testcrowd.model.testcases;

import java.util.Date;

public interface TestGroupsResult<G, U> {
    G getTestGroup();

    void setTestGroup(G testGroup);

    U getUser();

    void setUser(U user);

    TestGroupsResultStatus getTestGroupsResultStatus();

    void setTestGroupsResultStatus(TestGroupsResultStatus testGroupsResultStatus);

    Date getTakenOn();

    void setTakenOn(Date takenOn);

    Date getFinishedOn();

    void setFinishedOn(Date finishedOn);
}
