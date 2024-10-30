package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 07-服务消费者
 * 在前面的课程中，我们通过 Eureka Client 组件创建了一个服务提供者 provider，并且在注册中心完成注册，
 * 接下来其他微服务就可以访问 provider 相关服务了。
 *
 * 本节课我们就来实现一个服务消费者 consumer，调用 provider 相关接口，
 * 实现思路是先通过 Spring Boot 搭建一个微服务应用，再通过 Eureka Client 将其注册到 Eureka Server。
 * 此时的 provider 和 consumer 从代码的角度看并没有区别，都是 Eureka 客户端，
 * 我们人为地从业务角度对它们进行区分，provider 提供服务，consumer 调用服务，
 * 具体的实现需要结合 RestTemplate 来完成，
 * 即在服务消费者 consumer 中通过 RestTemplate 来调用服务提供者 provider 的相关接口。
 * @author ybl
 * @create 2024-07-20 23:55
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }

    //5、修改 ConsumerApplication 代码，创建 RestTemplate 实例并通过 @Bean 注解注入到 IoC 容器中
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
