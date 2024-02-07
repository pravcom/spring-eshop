package com.akhtyamov.springeshop.service;

import com.akhtyamov.springeshop.dao.BucketRepository;
import com.akhtyamov.springeshop.dao.ProductRepository;
import com.akhtyamov.springeshop.domain.Bucket;
import com.akhtyamov.springeshop.domain.Product;
import com.akhtyamov.springeshop.domain.User;
import com.akhtyamov.springeshop.dto.BucketDTO;
import com.akhtyamov.springeshop.dto.BucketDetailDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BucketServiceImpl implements BucketService{
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> products = getCollectRefProductsByIds(productIds);
        bucket.setProducts(products);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                //getOne вытаскивает ссылку на объект, findById - вытасквает сам объект
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProducts = products == null ? new ArrayList<>() : new ArrayList<>(products);

        newProducts.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProducts);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucketByUser(String name) {
        User user = userService.findByName(name);
        if (user==null || user.getBucket()==null){
            return new BucketDTO();
        }

        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();

        List<Product> products = user.getBucket().getProducts();

        for (Product product:products){
            BucketDetailDTO detail = mapByProductId.get(product.getId());
            if (detail == null){
                mapByProductId.put(product.getId(),  new BucketDetailDTO(product));
            }else {
                detail.setAmount(detail.getAmount().add( new BigDecimal(1)));
                detail.setSum((detail.getSum()) + Double.valueOf(product.getPrice().toString()));
            }

            bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
            bucketDTO.aggregate();
        }

        return bucketDTO;
    }

}
