package com.example.shop.services;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;
    public void saveRewiews(String text, Float star, Long product_id,
                            Long user_id) {
        reviewRepository.saveReview(text, star, product_id, user_id);
    }
    public List<ProductDto> ReviewAllStar() {
        return reviewRepository.ReviewAllStar();
    }

    public void deleteProductAndReviews(Long id) {
        reviewRepository.deleteReviewByID(id);
    }
}
