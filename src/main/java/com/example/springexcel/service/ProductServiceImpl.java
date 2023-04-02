package com.example.springexcel.service;

import com.example.springexcel.dao.ProductDao;
import com.example.springexcel.entity.Product;
import com.example.springexcel.helper.Helper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public String saveAllProducts(MultipartFile file) throws IOException {
        if(!Helper.ifExcelFile(file)){
            return "Bad request!\nPlease only give excel files as input.";
        }

        List<Product> products = Helper.convertExcelToList(file);
        productDao.saveAll(products);

        return "All Products saved successfully!";
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
}
