package com.pengjunlee.auth.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class ConfigPrinter implements CommandLineRunner {

    private final ConfigurableEnvironment environment;

    public ConfigPrinter(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> 当前加载的 Nacos 配置内容如下：");

        environment.getPropertySources().forEach(propertySource -> {
                System.out.println("配置源：" + propertySource.getName());
                Object source = propertySource.getSource();
                if (source instanceof java.util.Map<?, ?> map) {
                    map.forEach((key, value) -> {
                        System.out.printf("%s = %s\n", key, value);
                    });
                }
        });
    }
}
