package com.medium.machadoah.springboot.controllers;

import com.medium.machadoah.springboot.dto.ProductRecordDTO;
import com.medium.machadoah.springboot.models.ProductModel;
import com.medium.machadoah.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        // instanciando um objeto do tipo ProductModel
        var productModel = new ProductModel();

        // 🚨🚨 copiando as propriedades de productRecordDTO para productModel
        BeanUtils.copyProperties(productRecordDTO, productModel);

        // salvando o produto no banco de dados
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

}
