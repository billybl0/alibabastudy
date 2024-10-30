package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//Caused by: java.lang.NoClassDefFoundError: com/netflix/zuul/monitoring/CounterFactory
//import com.netflix.zuul.monitoring.CounterFactory;

/**
 * 08-用服务网关统一 URL，开发更简洁
 * 微服务网关使用场景
 * 在分布式项目架构中，我们会将服务进行拆分，不同的微服务负责各自的业务功能，实现软件架构层面的解耦合。
 * 但是如果拆分之后的微服务数量太多，是不利于系统开发的，因为每个服务都有不同的网络地址，
 * 客户端多次请求不同的微服务需要调用不同的 URL，如果同时去维护多个不同的 URL 无疑会增加开发的成本。
 * 如下图所示，一个外卖订餐系统，需要调用多个微服务接口才能完成一次订餐的业务流程，
 * 如果能有一种解决方案可以统一管理不同的微服务 URL，肯定会增强系统的维护性，提高开发效率。
 *
 * 这个解决方案就是 API 网关，API 网关对所有的 API 请求进行管理维护，相当于为系统开放出一个统一的接口，
 * 所有的外部请求只需要访问这个统一入口即可，系统内部再通过 API 网关去映射不同的微服务。
 * 对于开发者而言就不需要关注具体的微服务 URL 了，直接访问 API 网关接口即可，API 网关的结构如下图所示。
 * @author ybl
 * @create 2024-07-22 0:17
 */
//4、在启动类 GateWayApplication 添加@EnableZuulProxy、@EnableAutoConfiguration注解
//
//@EnableZuulProxy 包含 @EnableZuulServer 的功能，而且还加入了 @EnableCircuitBreaker 和 @EnableDiscoveryClient
//@EnableAutoConfiguration 将所有符合条件的 @Configuration 配置都加载到当前 Spring Boot 创建并使用的 IoC 容器。
//        5、依次启动注册中心、服务提供者 provider，运行 GateWayApplication。打开浏览器，访问 http://localhost:8761可以看到服务提供者 provider 和网关 gateway 已经在 Eureka Server 完成注册
//        6、通过 http://localhost:8030/p/student/findAll 访问 provider 提供的相关服务
//https://blog.csdn.net/qq_44774200/article/details/121324523
//@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
//    7、同时 Zuul 自带了负载均衡功能。在服务提供者 provider 添加返回当前服务端口的方法：
//
//@Value("${server.port}")
//private String port;
//
//@GetMapping("/index")
//public String index() {
//  return "当前端口：" + this.port;
//}

//    8、复制idea启动实例，设置VM options参数-Dserver.port=8011，再启动实例。最后重新启动 gateway，访问 http://localhost:8761
//可以看到当前注册中心有两个 provider 服务
//9、通过 gateway 来访问请求服务方法，端口为 8010 和 8011 的微服务交替被访问，实现了负载均衡

}
