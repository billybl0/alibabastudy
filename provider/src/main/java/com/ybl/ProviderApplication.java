package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 05-服务提供者
 * 服务提供者和服务消费者是从业务角度来划分的，
 * 实际上服务提供者和服务消费者都是通过 Eureka Client 连接到 Eureka Server 完成注册，
 * 本节课我们就来一起实现一个服务提供者，并且在 Eureka Server 完成注册，
 * 大致思路是先通过 Spring Boot 搭建一个微服务应用，再通过 Eureka Client 将其注册到 Eureka Server，
 * 创建 Eureka Client 的过程与创建 Eureka Server 十分相似，如下所示。
 * @author ybl
 * @create 2024-07-20 23:17
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }

    /**
     * 06-跨服务接口调用神器 RestTemplate
     * 在实现服务消费者之前，我们先来学习 RestTemplate 的使用，
     * 通过 RestTemplate 可以实现不同微服务之间的调用。
     *
     * 什么是 REST
     * REST 是当前比较流行的一种互联网软件架构模型，通过统一的规范完成不同终端的数据访问和交互，
     * REST 是一个词组的缩写，全称为 Representational State Transfer，翻译成中文的意思是资源表现层状态转化。
     *
     * 特点
     * 1、URL 传参更加简洁，如下所示
     *
     * 非 RESTful 的 URL：http://…../queryUserById?id=1
     * RESTful 的 URL：http://…./queryUserById/1
     * 2、完成不同终端之间的资源共享，RESTful 提供了一套规范，不同终端之间只需要遵守该规范，就可以实现数据交互。
     * RESTful 具体来讲就是四种表现形式，HTTP 协议中四种请求类型（GET、POST、PUT、DELETE）分别表示四种常规操作，
     * 即 CRUD：GET 获取资源、POST 创建资源、PUT 修改资源、DELETE 删除资源
     *
     * 什么是 RestTemplate
     * RestTemplate 是 Spring 框架提供的基于 REST 的服务组件，底层对 HTTP 请求及响应进行了封装，
     * 提供了很多访问远程 REST 服务的方法，可简化代码开发。
     * @return
     */
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }

}
