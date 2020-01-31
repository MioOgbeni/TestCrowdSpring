package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.review.ReviewImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    long count();

    ReviewImp save(ReviewImp review);

    void delete(ReviewImp review);

    List<ReviewImp> findAll();

    Page<ReviewImp> findAll(Pageable pageable);

    ReviewImp findById(String id);

}
