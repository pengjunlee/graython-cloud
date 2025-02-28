package com.pengjunlee.gateway;

import gray.bingo.starter.BingoStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pengjunlee.gateway","gray.bingo"})
public class GatewayApplication extends BingoStarter {
    public static void main(String[] args) {
        run(GatewayApplication.class, args);
    }
}
