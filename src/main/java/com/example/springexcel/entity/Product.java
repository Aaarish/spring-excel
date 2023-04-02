package com.example.springexcel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Product {
    @Id
    private Integer productId;
    private String productName;
    private String productDesc;
    private Integer productPrice;
}
