package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 09-Ribbon 负载均衡
 * 在前面的课程中我们已经通过 RestTemplate 实现了服务消费者对服务提供者的调用，这只是实现了最基本的需求，
 * 如果在某个具体的业务场景下，对于某服务的调用需求激增，这时候我们就需要为该服务实现负载均衡以满足高并发访问，
 * 在一个大型的分布式应用系统中，负载均衡(Load Balancing)是必备的。
 *
 * 什么是 Ribbon？
 * Spring Cloud 提供了实现负载均衡的解决方案：Spring Cloud Ribbon，Ribbon 是 Netflix 发布的负载均衡器，
 * 而 Spring Cloud Ribbon 则是基于 Netflix Ribbon 实现的，是一个用于对 HTTP 请求进行控制的负载均衡客户端。
 *
 * Spring Cloud Ribbon 官网地址：http://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-ribbon.html
 *
 * Ribbon 的使用同样需要结合 Eureka Server，即需要将 Ribbon 在 Eureka Server 进行注册，注册完成之后，
 * 就可以通过 Ribbon 结合某种负载均衡算法，如随机、轮询、加权随机、加权轮询等帮助服务消费者去调用接口。
 * 除了 Ribbon 默认提供的这些负载均衡算法外，开发者也可以根据具体需求来设计自定义的 Ribbon 负载均衡算法。
 * 实际开发中，Spring Cloud Ribbon 需要结合 Spring Cloud Eureka 来使用，
 * Eureka Server 提供所有可调用的服务提供者列表，
 * Ribbon 基于特定的负载均衡算法从这些服务提供者中挑选出要调用的实例，如下图所示。
 * Ribbon 常用的负载均衡策略有以下几种：
 * 随机：访问服务时，随机从注册中心的服务列表中选择一个。
 * 轮询：当同时启动两个服务提供者时，客户端请求会由这两个服务提供者交替处理。
 * 加权轮询：对服务列表中的所有微服务响应时间做加权处理，并以轮询的方式来访问这些服务。
 * 最大可用：从服务列表中选择并发访问量最小的微服务。
 * @author ybl
 * @create 2024-07-28 0:17
 */
@SpringBootApplication
public class RibbonAppliction {
    public static void main(String[] args) {
        SpringApplication.run(RibbonAppliction.class);
    }
//    4、通过 @Bean 注解注入 RestTemplate 实例，@LoadBalanced 注解提供负载均衡。
@Bean
@LoadBalanced // 声明一个基于 Ribbon 的负载均衡
public RestTemplate restTemplate() {
    return new RestTemplate();
}

}
