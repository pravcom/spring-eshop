package com.akhtyamov.springeshop.service;

import com.akhtyamov.springeshop.domain.Bucket;
import com.akhtyamov.springeshop.domain.User;
import com.akhtyamov.springeshop.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long>productIds);
    void addProduct(Bucket bucket,List<Long>productIds);
    BucketDTO getBucketByUser(String name);
}
