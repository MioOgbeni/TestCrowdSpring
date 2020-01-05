package cz.spitsoft.testcrowd.model;

public interface TestCase {
    String id = null;
    String name = null;

    String getId();

    void setId(String id);

    String getName();

    void setName(String name);
}
