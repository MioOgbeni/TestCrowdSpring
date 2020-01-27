package cz.spitsoft.testcrowd.model.test_case;

public enum TestStatus {
    AVAILABLE, // test is available for take
    TAKEN, // test is taken (test can not be taken)
    DONE, // test is done (test can not be taken)
    CANCELED // test can not be taken
}
