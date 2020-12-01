package com.orange.pocs.grpc.pingpong;

import com.orange.pocs.grpc.pingpong.service.PingPongGRPCServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Log4j2
public class PingPongGRPCServiceTest {

    @Autowired
    private PingPongGRPCServiceImpl grpcClientService;

    @Test
    public void ping_happy_path() {
        grpcClientService.ping();
    }
}
