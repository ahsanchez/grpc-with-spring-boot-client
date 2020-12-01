package com.orange.pocs.grpc.product.restapi.service;

import com.orange.pocs.grpc.product.restapi.model.ProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductRestServiceTest {

    @Autowired
    private ProductRestServiceImpl productRestService;

    @Test
    public void rest_add_product_get_product_elapsed_times() {
        double start = getTime();
        String response = productRestService.addProduct(new ProductRequest("LG Q6", "Company smartphone", (long) 500.25));
        System.out.println("Add product elapsed time:  " + getDiffInSeconds(start, getTime()));
        start = getTime();
        productRestService.getProduct(response);
        System.out.println("Get product elapsed time:  " + getDiffInSeconds(start, getTime()));
    }

    private double getTime() {
        return System.currentTimeMillis();
    }

    private double getDiffInSeconds(double start, double end) {
        return (end - start) / 1000;
    }
}
