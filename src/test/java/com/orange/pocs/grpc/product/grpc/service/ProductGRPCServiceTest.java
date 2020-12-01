package com.orange.pocs.grpc.product.grpc.service;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Log4j2
public class ProductGRPCServiceTest {

    @Autowired
    private ProductGRPCServiceImpl productService;

    @Test
    public void grpc_add_product_get_product_elapsed_times() {
        double start = getTime();
        String response = productService.addProduct(Product.newBuilder().setName("LG Q6").setDescription("LG smartphone").setPrice((long) 500.25).build());
        System.out.println("Add product elapsed time:  " + getDiffInSeconds(start, getTime()));
        start = getTime();
        productService.getProduct(response);
        System.out.println("Get product elapsed time:  " + getDiffInSeconds(start, getTime()));
    }

    private double getTime() {
        return System.currentTimeMillis();
    }

    private double getDiffInSeconds(double start, double end) {
        return (end - start) / 1000;
    }
}
