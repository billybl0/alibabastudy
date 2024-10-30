package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;
//import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author ybl
 * @create 2024-08-18 15:34
 */
//4、在启动类 ZipkinApplication 添加注解
@EnableZipkinServer // 声明启动 Zipkin Server

@SpringBootApplication
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class);

    }
}
