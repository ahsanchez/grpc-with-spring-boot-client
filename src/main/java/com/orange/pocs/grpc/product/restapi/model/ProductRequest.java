package com.orange.pocs.grpc.product.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {
    private String id;
    private String name;
    private String description;
    private float price;

    public ProductRequest(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
