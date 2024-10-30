package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 5、依次启动注册中心、configserver、configclient。
 *
 * 通过控制台输出信息可以看到，configclient 已经读取到了 Git 仓库中的配置信息。
 * 6、访问请求服务方法
 *
 * curl -X GET http://localhost:8070/config/index
 * @author ybl
 * @create 2024-08-18 17:48
 */
@SpringBootApplication
public class ConfigClient2Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient2Application.class);
    }
}
