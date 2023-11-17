package com.medium.machadoah.springboot.controllers;

import com.medium.machadoah.springboot.dto.ProductRecordDTO;
import com.medium.machadoah.springboot.models.ProductModel;
import com.medium.machadoah.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable(value = "id") UUID id) {
        var productModel = productRepository.findById(id);
        if(productModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModel.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDTO productRecordDTO) {
        var productModel = productRepository.findById(id);
        if(productModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        BeanUtils.copyProperties(productRecordDTO, productModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel.get()));
    }

}
