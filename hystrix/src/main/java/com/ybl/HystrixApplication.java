package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 11-Hystrix 容错监控机制
 * 什么是微服务容错机制？
 * 在一个分布式系统中，各个微服务之间相互调用、彼此依赖，实际运行环境中可能会因为各种个样的原因导致某个微服务不可用，
 * 则依赖于该服务的其他微服务就会出现响应时间过长或者请求失败的情况，如果这种情况出现的次数较多，
 * 从一定程度上就可以能导致整个系统崩溃，如何来解决这一问题呢？
 *
 * 在不改变各个微服务调用关系的前提下，可以针对这些错误情况提前设置处理方案，遇到问题时，整个系统可以自主进行调整，
 * 这就是微服务容错机制的原理，发现故障并及时处理。
 *
 * 什么是 Hystrix？
 * Hystrix 是 Netflix 的一个开源项目，是一个熔断器模型的具体实现，熔断器就类似于电路中的保险丝，
 * 某个单元发生故障就通过烧断保险丝的方式来阻止故障蔓延，微服务架构的容错机制也是这个原理。
 * Hystrix 的主要作用是当服务提供者发生故障无法访问时，Hystrix向服务消费者返回 fallback 降级处理，
 * 从而避免响应时间过长或者直接抛出异常的情况
 *
 * Hystrix 设计原则
 * 服务隔离机制，防止某个服务提供者出问题而影响到整个系统的运行
 * 服务降级机制，服务出现故障时向服务消费者返回 fallback 处理机制
 * 熔断机制，当服务消费者请求的失败率达到某个特定数值时，会迅速启动熔断机制并进行修复
 * 提供实时的监控和报警功能，迅速发现服务中存在的故障
 * 提供实时的配置修改功能，保证故障问题可以及时得到处理和修复
 * 上节课中我们演示了 Hystrix 的熔断机制，本节课重点介绍 Hystrix 的另外一个重要功能——数据监控
 *
 * Hystrix 除了可以为服务提供容错机制外，同时还提供了对请求的监控，这个功能需要结合 Spring Boot Actuator 来使用，
 * Actuator 提供了对服务的健康监控、数据统计功能，可以通过 hystrix-stream 节点获取监控到的请求数据，
 * Dashboard 组件则提供了数据的可视化监控功能
 * @author ybl
 * @create 2024-07-28 22:51
 */
//4、在启动类 HystrixApplication 添加注解
@SpringBootApplication
@EnableFeignClients//声明启用Feign
@EnableCircuitBreaker//声明启用数据监控
@EnableHystrixDashboard//声明启用可视化数据监控
public class HystrixApplication {
    //6、依次启动注册中心、Provider、Hystrix。
    //
    //打开浏览器访问 http://localhost:8060/actuator/hystrix.stream 查看监控数据，此时没有客户端请求，所以没有数据。
    //
    //访问 http://localhost:8060/hystrix/index 后，再次访问 http://localhost:8060/actuator/hystrix.stream，可以看到显示了监控数据情况，但是这种方式并不直观，通过可视化界面的方式能够更加直观地进行监控数据
    //
    //7、通过 Dashboard 实现可视化监控
    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class);
    }
}

