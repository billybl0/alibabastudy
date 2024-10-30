package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 5、依次启动注册中心、Zipkin、ZipkinClient。打开浏览器访问http://localhost:9090/zipkin/，可看到 Zipkin 首页。
 * 6、点击 Find Traces 按钮可看到监控数据情况，当前没有监控到任何数据。
 * 7、访问请求后再次刷新 http://localhost:9090/zipkin/，可看到监控数据，点击可查看详情。
 * curl -X GET http://localhost:8090/zipkin/index
 * @author ybl
 * @create 2024-08-18 15:43
 */
@SpringBootApplication
public class ZipkinClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinClientApplication.class);

    }
}
