package cz.spitsoft.testcrowd.model.test_case;

import cz.spitsoft.testcrowd.model.file.FileImp;
import cz.spitsoft.testcrowd.model.user.UserImp;

import java.util.Date;
import java.util.List;

public interface TestResult {

    String getDescription();

    void setDescription(String description);

    TestCaseImp getTestCase();

    void setTestCase(TestCaseImp testCase);

    UserImp getUser();

    void setUser(UserImp user);

    List<FileImp> getFiles();

    void setFiles(List<FileImp> evidences);

    TestResultStatus getTestResultStatus();

    void setTestResultStatus(TestResultStatus testResultStatus);

    int getReward();

    void setReward(int reward);

    Date getTakenAt();

    void setTakenAt(Date takenAt);

    Date getFinishedAt();

    void setFinishedAt(Date finishedAt);

}
