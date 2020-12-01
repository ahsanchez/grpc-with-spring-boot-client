package com.orange.pocs.grpc.product.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {
    private String id;
    private String name;
    private String description;
    private long price;

    public ProductRequest(String name, String description, long price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
