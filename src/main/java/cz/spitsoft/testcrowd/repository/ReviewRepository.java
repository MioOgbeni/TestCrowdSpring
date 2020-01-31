package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.review.ReviewImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewImp, UUID> {

    @Nonnull
    List<ReviewImp> findAll();

    @Nonnull
    Page<ReviewImp> findAll(@Nonnull Pageable pageable);

    ReviewImp findById(String id);

}
