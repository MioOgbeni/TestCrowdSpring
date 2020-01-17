package cz.spitsoft.testcrowd.model.reviews;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.UserImp;
import org.hibernate.annotations.Target;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "REVIEW_ID"))
})
@Table(name = "TBL_REVIEWS")
public class ReviewImp<U> extends BaseEntity implements Review<U> {
    @Column(name = "CONTENT")
    @Size(max = 255, message = "{review.content.invalid}")
    private String content;

    @Column(name = "RATING")
    @Size(min = 1, max = 5, message = "{review.rating.invalid}")
    @NotEmpty
    private int rating;

    @Column(name = "CREATED_ON")
    @NotEmpty
    private Date createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREATED_BY")
    @Target(UserImp.class)
    @NotEmpty
    private U createdBy;

    public ReviewImp() {
        super();
    }

    public ReviewImp(String content, int rating, Date createdOn, U createdBy) {
        super();
        this.content = content;
        this.rating = rating;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public U getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("createdOn", this.getCreatedOn()).toString();
    }
}
