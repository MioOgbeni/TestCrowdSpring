package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.user.UserImp;

import java.util.Date;

public interface TestGroupResult {

    TestGroupImp getTestGroup();

    void setTestGroup(TestGroupImp testGroup);

    UserImp getUser();

    void setUser(UserImp user);

    TestGroupResultStatus getTestGroupResultStatus();

    void setTestGroupResultStatus(TestGroupResultStatus testGroupResultStatus);

    Date getTakenAt();

    void setTakenAt(Date takenAt);

    Date getFinishedAt();

    void setFinishedAt(Date finishedAt);

}
