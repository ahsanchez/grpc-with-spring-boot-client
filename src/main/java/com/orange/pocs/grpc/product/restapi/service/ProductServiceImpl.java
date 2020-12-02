package com.orange.pocs.grpc.product.restapi.service;

import com.orange.pocs.grpc.product.restapi.model.ProductIdResponseRequest;
import com.orange.pocs.grpc.product.restapi.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl {

    @Value("${remote.restapi.ms2.product.rest.url}")
    private String restUrl;

    @Value("${remote.restapi.ms2.product.grpc.url}")
    private String grpcUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String addProductRest(ProductRequest request) {
        ProductIdResponseRequest response = restTemplate.postForObject(restUrl, new HttpEntity<>(request), ProductIdResponseRequest.class);
        return response != null ? response.getValue() : "";
    }

    public ProductRequest getProductRest(String productId) {
        String path = new StringBuilder(restUrl).append("/").append(productId).toString();
        return restTemplate.getForObject(path, ProductRequest.class);
    }

    public String addProductGRPC(ProductRequest request) {
        ProductIdResponseRequest response = restTemplate.postForObject(grpcUrl, new HttpEntity<>(request), ProductIdResponseRequest.class);
        return response != null ? response.getValue() : "";
    }

    public ProductRequest getProductRestGRPC(String productId) {
        String path = new StringBuilder(grpcUrl).append("/").append(productId).toString();
        return restTemplate.getForObject(path, ProductRequest.class);
    }

}
