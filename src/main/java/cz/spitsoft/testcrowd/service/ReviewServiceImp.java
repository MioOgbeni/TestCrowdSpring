package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.review.ReviewImp;
import cz.spitsoft.testcrowd.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public long count() {
        return reviewRepository.count();
    }

    @Override
    public ReviewImp save(ReviewImp review) {
        return reviewRepository.save(review);
    }

    @Override
    public void delete(ReviewImp review) {
        reviewRepository.delete(review);
    }

    @Override
    public List<ReviewImp> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Page<ReviewImp> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public ReviewImp findById(String id) {
        return reviewRepository.findById(id);
    }

}
