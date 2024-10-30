package com.ybl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author ybl
 * @create 2024-07-28 23:26
 */
//5、在启动类 NativeConfigServerApplication 添加注解
@EnableConfigServer//声明配置中心
@SpringBootApplication
public class NativeConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(NativeConfigApplication.class);
        //本地配置中心已经创建完成，接下来创建客户端来读取本地配置中心的配置文件
    }
}
