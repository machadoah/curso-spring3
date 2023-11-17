package com.medium.machadoah.springboot.controllers;

import com.medium.machadoah.springboot.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    ProductRepository productRepository;


}
