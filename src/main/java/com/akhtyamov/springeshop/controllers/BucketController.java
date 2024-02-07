package com.akhtyamov.springeshop.controllers;

import com.akhtyamov.springeshop.dto.BucketDTO;
import com.akhtyamov.springeshop.service.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping
    public String aboutBucket(Model model, Principal principal){
        if (principal==null){
            model.addAttribute("bucket", new BucketDTO());
        }else {
            BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }
        return "bucket";
    }

}
