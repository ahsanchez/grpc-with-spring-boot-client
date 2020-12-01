package com.orange.pocs.grpc.product.restapi.service;

import com.orange.pocs.grpc.product.restapi.model.ProductIdResponseRequest;
import com.orange.pocs.grpc.product.restapi.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductRestServiceImpl {

    @Value("${remote.server.restapi.host}")
    private String host;

    @Value("${remote.server.restapi.port}")
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    public String addProduct(ProductRequest request) {
        String uri = host + ":" + port + "/product";
        ProductIdResponseRequest response = restTemplate.postForObject(uri, new HttpEntity<>(request), ProductIdResponseRequest.class);
        return response != null ? response.getValue() : "";
    }

    public ProductRequest getProduct(String productId) {
        String uri = host + ":" + port + "/product/" + productId;
        return restTemplate.getForObject(uri, ProductRequest.class);
    }

}
