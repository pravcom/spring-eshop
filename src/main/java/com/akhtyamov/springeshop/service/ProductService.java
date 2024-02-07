package com.akhtyamov.springeshop.service;

import com.akhtyamov.springeshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void addToUserBucket(Long productId, String username);
}
