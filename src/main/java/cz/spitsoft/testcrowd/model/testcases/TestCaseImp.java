package cz.spitsoft.testcrowd.model.testcases;

import cz.spitsoft.testcrowd.model.BaseEntity;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "TESTCASE_ID"))
})
@Table(name = "TBL_TESTCASES")
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew()).append("name", this.getName()).toString();
    }
}
