package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 10-Spring Cloud Feign 声明式接口调用
 * 什么是 Feign
 * 与 Ribbon 一样，Feign 也是由 Netflix 提供的，
 * Feign 是一个提供模版的声明式 Web Service 客户端， 使用 Feign 可以简化 Web Service 客户端的编写，
 * 开发者可以通过简单的接口和注解来调用 HTTP API，使得开发更加快捷、方便。
 * Spring Cloud 也提供了 Feign 的集成组件：Spring Cloud Feign，它整合了 Ribbon 和 Hystrix，
 * 具有可插拔、基于注解、负载均衡、服务熔断等一系列便捷功能。
 *
 * 在 Spring Cloud 中使用 Feign 非常简单，我们说过 Feign 是一个声明式的 Web Service 客户端，
 * 所以只需要创建一个接口，同时在接口上添加相关注解即可完成服务提供方的接口绑定，
 * 相比较于 Ribbon + RestTemplate 的方式，Feign 大大简化了代码的开发，
 * Feign 支持多种注解，包括 Feign 注解、JAX-RS 注解、Spring MVC 注解等。
 * Spring Cloud 对 Feign 进行了优化，整合了 Ribbon 和 Eureka，从而让 Feign 的使用更加方便。
 *
 * 我们说过 Feign 是一种比 Ribbon 更加方便好用的 Web 服务客户端，那么二者有什么具体区别呢？Feign 好用在哪里？
 *
 * Ribbon 与 Feign 的区别
 * 关于 Ribbon 和 Feign 的区别可以简单地理解为
 * Ribbon 是个通用的 HTTP 客户端工具，
 * 而 Feign 则是基于 Ribbon 来实现的，同时它更加灵活，使用起来也更加简单。
 * 上节课中我们通过 Ribbon + RestTemplate 实现了服务调用的负载均衡，相比较于这种方式，
 * 使用 Feign 可以直接通过声明式接口的形式来调用服务，非常方便，比 Ribbon 使用起来要更加简便，
 * 只需要创建接口并添加相关注解配置，即可实现服务消费的负载均衡。
 *
 * Feign 的特点
 * Feign 是一个声明式 Web Service 客户端。
 * 支持 Feign 注解、JAX-RS 注解、Spring MVC 注解。
 * Feign 基于 Ribbon 实现，使用起来更加简单。
 * Feign 集成了 Hystrix，具备服务熔断功能。
 * @author ybl
 * @create 2024-07-28 22:03
 */
//4、在启动类 FeignApplication 添加@EnableFeignClients注解，用于声明启用 Feign
@SpringBootApplication
@EnableFeignClients
public class FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class);
    }


}
