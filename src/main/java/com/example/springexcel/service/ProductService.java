package com.example.springexcel.service;

import com.example.springexcel.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    String saveAllProducts(MultipartFile file) throws IOException;

    List<Product> getAllProducts();
}
