package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 04-注册中心
 * 服务治理的核心组成有三部分：服务提供者，服务消费者，注册中心。
 *
 * 在分布式系统架构中，每个微服务（服务提供者、服务消费者）在启动时，将自己的信息存储在注册中心，
 * 我们把这个过程称之为服务注册。服务消费者要调用服务提供者的接口，就得找到服务提供者，
 * 从注册中心查询服务提供者的网络信息，并通过此信息来调用服务提供者的接口，这个过程就是服务发现。
 *
 * 既然叫服务治理就不仅仅是服务注册与服务发现，同时还包括了服务的管理，
 * 即注册中心需要对记录在案的微服务进行统一管理，如何来具体实现管理呢？
 * 各个微服务与注册中心通过心跳机制完成通信，每间隔一定时间微服务就会向注册中心汇报一次，
 * 如果注册中心长时间无法与某个微服务通信，就会自动销毁该微服务。
 * 当某个微服务的网络信息发生变化时，会重新注册。同时，微服务可以通过客户端缓存将需要调用的服务地址保存起来，
 * 并通过定时任务更新的方式来保证服务的时效性，这样可以降低注册中心的压力，如果注册中心出现问题，
 * 也不会影响微服务之间的调用。
 *
 * 服务提供者、服务消费者、注册中心的关联
 * 首先启动注册中心。
 * 服务提供者启动时，在注册中心注册可以提供的服务。
 * 服务消费者启动时，在注册中心订阅需要调用的服务。
 * 注册中心将服务提供者的信息推送给服务调用者。
 * 服务调用者通过相关信息（IP、端口等）调用服务提供者的服务。
 * 注册中心核心模块
 * 服务注册表：用来存储各个微服务的信息，注册中心提供 API 来查询和管理各个微服务。
 * 服务注册：微服务在启动时，将自己的信息在保存在注册中心。
 * 服务发现：查询需要调用的微服务的网络信息，如 IP 地址、端口。
 * 服务检查：通过心跳机制与完成注册的各个微服务完成通信。如果微服务长时间无法访问则销毁该服务，
 * 网络信息发生变化则重新注册。
 * Spring Cloud 的服务治理可以使用 Consul 和 Eureka 组件，这里我们选择 Eureka。
 *
 * 什么是 Eureka？
 * Eureka 是 Netflix 开源的基于 REST 的服务治理解决方案，Spring Cloud 集成了 Eureka，即 Spring Cloud Eureka，
 * 提供服务注册和服务发现功能，可以和基于 Spring Boot 搭建的微服务应用轻松完成整合，开箱即用，
 * 实现 Spring Cloud 的服务治理功能。Spring Cloud 对 Netflix 开源组件进行了二次封装，
 * 也就是 Spring Cloud Netflix，Spring Cloud Eureka 是 Spring Cloud Netflix 微服务套件中的一部分，
 * 基于 Netflix Eureka 实现了二次封装，实际开发中，我们就使用 Spring Cloud Eureka 来完成服务治理。
 *
 * Spring Cloud Eureka 的组成
 * Spring Cloud Eureka 主要包含了服务端和客户端组件：Eureka Server 服务端、Eureka Client 客户端。
 *
 * Eureka Server，是提供服务注册与发现功能的服务端，也称作服务注册中心，Eureka 支持高可用的配置。
 * Eureka Client 是客户端组件，它的功能是将微服务在 Eureka Server 完成注册和后期维护功能，包括续租、注销等。
 * 需要注册的微服务就是通过 Eureka Client 连接到 Eureka Server 完成注册的，通
 * 过心跳机制实现注册中心与微服务的通信，完成对各个服务的状态监控。
 * 简单理解注册中心（Eureka Server）就相当于一个电商平台，
 * 服务提供者（Eureka Client）相当于卖家在平台上注册了一个店铺，提供出售商品的服务，
 * 服务消费者（另一个Eureka Client）相当于用户在平台上注册账号，然后就可以在平台的各个店铺中购买商品了，
 * 同时平台（Eureka Server）还提供管理买家与卖家信息的功能，比如卖家是否在线、可以提供哪些具体服务等等。
 * 原文链接：https://blog.csdn.net/qq_44774200/article/details/121324523
 * @author ybl
 * @create 2024-07-13 23:51
 * 6、在启动类 RegistryCenterApplication添加@EnableEurekaServer注解。
 * 声明该类是一个 Eureka Server 微服务，提供发现服务的功能，即注册中心。
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryCenterApplication {
    public static void main(String[] args) {
        //7、启动运行RegistryCenterApplication，打开浏览器访问http://localhost:8761，注册中心启动成功。
        SpringApplication.run(RegistryCenterApplication.class);
    }
}
