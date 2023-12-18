package com.example.shop.services;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.Login;
import com.example.shop.models.Review;
import com.example.shop.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;
    public void saveRewiews(Review review) {
        reviewRepository.saveReview(review);
    }
    public List<ProductDto> ReviewAllStar() {
        return reviewRepository.ReviewAllStar();
    }


//    public void deleteProductAndReviews(Long id) {
//        reviewRepository.deleteReviewByID(id);
//    }

    @Transactional
    public void deleteReviewById(Long id) {
        reviewRepository.deleteReviewById(id);
    }
}
