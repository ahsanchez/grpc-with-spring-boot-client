package com.orange.pocs.grpc.product.grpc.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductGRPCServiceImpl {

    @Value("${remote.server.grpc.host}")
    private String host;

    @Value("${remote.server.grpc.port}")
    private int port;

    public String addProduct(Product product) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);
        ProductId productId = stub.addProduct(product);
        channel.shutdown();
        return productId.getValue();
    }

    public Product getProduct(String productId) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);
        Product product = stub.getProduct(ProductId.newBuilder().setValue(productId).build());
        channel.shutdown();
        return product;
    }
}
