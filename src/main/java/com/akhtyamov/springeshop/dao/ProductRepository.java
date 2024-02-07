package com.akhtyamov.springeshop.dao;

import com.akhtyamov.springeshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
