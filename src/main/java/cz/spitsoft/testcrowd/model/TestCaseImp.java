package cz.spitsoft.testcrowd.model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_TESTCASE")
public class TestCaseImp extends BaseEntity implements TestCase {

    @Column(name = "NAME")
    @Size(max = 20, min = 3, message = "{testcase.name.invalid}")
    @NotEmpty
    private String name;

    public TestCaseImp() {
        super();
    }

    public TestCaseImp(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew()).append("name", this.getName()).toString();
    }
}
