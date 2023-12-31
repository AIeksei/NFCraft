package com.example.shop.services;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public List<ProductDto> allProducts() {
        return productRepository.listProducts();
    }

    public List<ProductDto> findByIdProducts(Long newID) {
        return productRepository.cardProduct(newID);
    }

    public void markProductAsDeleted(long productId) {
        productRepository.updateProductDeletedStatus(productId);
    }

    public List<ProductDto> awgReviewStar(Long newID) {
        return productRepository.awgReviewStar(newID);
    }
    public List<ProductDto> ReviewStar(Long newID) {
        return productRepository.ReviewStar(newID);
    }


    @Transactional
    public void updateProductInfo(Long productId, String info, String name, Float price, Long quant, Long categoryId) {
        productRepository.updateProductInfo(info, name, price, quant, categoryId, productId);
    }

    @Transactional
    public void saveProduct(String info, String name, Float price, Long quant, Long categoryId,Boolean is_deleted) {
        productRepository.saveProduct(info, name, price, quant, categoryId,is_deleted);
    }
}