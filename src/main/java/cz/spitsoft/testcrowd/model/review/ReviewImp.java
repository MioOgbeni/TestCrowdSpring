package cz.spitsoft.testcrowd.model.review;

import cz.spitsoft.testcrowd.model.BaseEntity;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.hibernate.annotations.Target;
import org.hibernate.validator.constraints.Range;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "ID", column = @Column(name = "REVIEW_ID"))
})
@Table(name = "TBL_REVIEWS")
public class ReviewImp extends BaseEntity implements Review {

    @Column(name = "CONTENT")
    @Size(max = 240, message = "{review.content.invalid}")
    private String content;

    @Column(name = "RATING")
    @Range(min = 1, max = 5, message = "{review.rating.invalid}")
    @NotNull
    private int rating;

    @Column(name = "CREATED_AT")
    @NotNull
    private Date createdAt;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "CREATED_BY")
    @Target(UserImp.class)
    @NotNull
    private UserImp createdBy;

    public ReviewImp() {
        super();
    }

    public ReviewImp(String content, int rating, Date createdAt, UserImp createdBy) {
        super();
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
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
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public UserImp getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(UserImp createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("createdAt", this.getCreatedAt())
                .toString();
    }

}
