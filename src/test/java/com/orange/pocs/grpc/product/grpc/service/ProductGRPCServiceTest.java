package com.orange.pocs.grpc.product.grpc.service;

import com.orange.pocs.grpc.product.restapi.model.ProductRequest;
import com.orange.pocs.grpc.product.restapi.service.ProductServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Log4j2
public class ProductGRPCServiceTest {

    @Autowired
    private ProductServiceImpl productService;

    private List<ProductRequest> requestPool;

    private static final int THREAD_POOL = 5;
    private static final int MAX_THREADS_NUMBER = 5;
    private static final int AWAIT_TERMINATION_SECONDS = 3;

    @Before
    public void setUp() {
        requestPool = new ArrayList<>();
        requestPool.add(new ProductRequest("LG Q6", "LG smartphone", 52.6F));
        requestPool.add(new ProductRequest("Hisense 5254L", "Smart TV Hisense", 1450.25F));
        requestPool.add(new ProductRequest("iPhone X", "iPhone X 512 GB", 1523.6F));
        requestPool.add(new ProductRequest("iWatch Serie 3", "iWatch Serie 3 space black", 3563.20F));
        requestPool.add(new ProductRequest("Lenovo T480", "Notebook Lenovo T480 black W10", 8023.50F));
    }

    @Test
    public void rest_add_product_thread_pool() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL);
        List<Callable<String>> callables = new ArrayList<>();
        IntStream.range(0, MAX_THREADS_NUMBER).forEach(count -> callables.add(this::callableAddProduct));
        service.invokeAll(callables);
        service.awaitTermination(AWAIT_TERMINATION_SECONDS, TimeUnit.SECONDS);
    }

    @Test
    public void rest_get_product_thread_pool() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL);
        List<Callable<ProductRequest>> callables = new ArrayList<>();
        IntStream.range(0, MAX_THREADS_NUMBER).forEach(count -> callables.add(this::callableGetProduct));
        service.invokeAll(callables);
        service.awaitTermination(AWAIT_TERMINATION_SECONDS, TimeUnit.SECONDS);
    }

    @Test
    public void grpc_add_product_thread_pool() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL);
        List<Callable<String>> callables = new ArrayList<>();
        IntStream.range(0, MAX_THREADS_NUMBER).forEach(count -> callables.add(this::grpcCallableAddProduct));
        service.invokeAll(callables);
        service.awaitTermination(AWAIT_TERMINATION_SECONDS, TimeUnit.SECONDS);
    }

    @Test
    public void grpc_get_product_thread_pool() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL);
        List<Callable<ProductRequest>> callables = new ArrayList<>();
        IntStream.range(0, MAX_THREADS_NUMBER).forEach(count -> callables.add(this::grpcCallableGetProduct));
        service.invokeAll(callables);
        service.awaitTermination(AWAIT_TERMINATION_SECONDS, TimeUnit.SECONDS);
    }

    private String callableAddProduct() {
        return productService.addProductRest(requestPool.get(generateRandomNumberBtw0AndGivenNumber(requestPool.size())));
    }

    private ProductRequest callableGetProduct() {
        return productService.getProductRest("test");
    }

    private String grpcCallableAddProduct() {
        return productService.addProductGRPC(requestPool.get(generateRandomNumberBtw0AndGivenNumber(requestPool.size())));
    }

    private ProductRequest grpcCallableGetProduct() {
        return productService.getProductRestGRPC("test");
    }

    public int generateRandomNumberBtw0AndGivenNumber(int high) {
        Random r = new Random();
        int low = 0;
        return r.nextInt(high - low) + low;
    }

}
