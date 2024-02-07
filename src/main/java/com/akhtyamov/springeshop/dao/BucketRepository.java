package com.akhtyamov.springeshop.dao;

import com.akhtyamov.springeshop.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
