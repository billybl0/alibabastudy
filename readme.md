01-Spring Boot 快速入门
Spring Boot 简介
Spring 作为一个软件设计层面的框架，在 Java 企业级开发中应用非常广泛，但是 Spring 框架的配置非常繁琐，且大多是重复性的工作，Spring Boot 的诞生就解决了这一问题，通过 Spring Boot 可以快速搭建一个基于 Spring 的 Java 应用程序。Spring Boot 对常用的第三方库提供了配置方案，可以很好地与 Spring 进行整合，如 MyBatis、Spring Data JPA 等，可以一键式搭建功能完备的 Java 企业级应用程序。

Spring Boot 的优势：
不需要任何 XML 配置文件。
内嵌 Web 服务器，可直接部署。
默认支持 JSON 数据，不需要额外配置。
支持 RESTful 风格。
最少一个配置文件可以配置所有的个性化信息。
简而言之，Spring Boot 就是一个用很少的配置就可以快速搭建 Spring 应用的框架，并且很好的集成了常用第三方库，让开发者能够快速进行企业级项目开发。
创建 Spring Boot 工程
有 3 种常用方式可以快速创建一个 Spring Boot 工程，接下来详细给大家介绍每种方式的具体操作。

1、在线创建工程

打开浏览器访问 https://start.spring.io，可在线创建一个 Spring Boot 工程

选择创建工程的方式为Maven、语言为Java，
Spring Boot 的版本选择 ，
输入 Group Id 和 Artifact Id
选择需要依赖的模块。
点击下方的 Generate 按钮即可下载模版的压缩文件，解压后用 IDEA 打开即可。
2、使用 IDEA Spring Initializr 创建工程
打开 IDEA，选择 Create New Project；
选择 Spring Initializr，点击 Next，可以看到实际上 IDEA 还是通过 https://start.spring.io 来创建工程的；
输入 GroupId、ArtifactId 等基本信息，点击 Next；
选择需要依赖的模块，点击 Next；
选择项目路径，点击 Finish 即可完成创建。
3、手动创建 Spring Boot 工程
打开 IDEA，选择 Create New Project；
选择 Maven，点击 Next；
输入 GroupId 和 ArtifactId，点击 Next；
选择项目路径，点击 Finish 即可创建一个空的 Maven 工程。
手动添加 Spring Boot 相关依赖，在 parent 标签中配置 spring-boot-starter-parent 的依赖，相当于给整个工程配置了一个 Spring Boot 的父依赖，其他模块直接在继承父依赖的基础上添加特定依赖即可。
比如现在要集成 Web MVC 组件，直接在 dependencies 中添加一个 spring-boot-starter-web 依赖即可，默认使用 Tomcat 作为 Web 容器，pom.xml 如下所示。

<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.1.5.RELEASE</version>
</parent>

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
</dependencies>

使用 Spring Boot
通过以上任意一种方式都可快速搭建一个 Spring Boot 工程，然后就可以根据需求添加各种子模块依赖了，比如上述的第 3 种方式，我们添加了 Web MVC 组件，当前工程就成为了一个 Spring MVC 框架项目，开发者可以按照 Spring MVC 的开发步骤直接写代码了，同时 Spring Boot 还帮助我们大大简化了配置文件。
你可以拿当前的工程和之前课程中我们创建的 Spring MVC 工程对比一下，会发现不需要在 web.xml 中配置 DispatcherServlet，同时也不需要创建 springmvc.xml 了。
传统 Spring MVC 工程的 springmvc.xml 中主要添加三个配置，一是启用注解驱动，二是自动扫包，三是视图解析器。Spring Boot 自动帮我们搞定了前两个配置，第三个视图解析器需要开发者手动配置，因为视图层资源的存储路径和文件类型框架是没有办法自动获取的，不同工程的具体方式也不一样，像这种个性化的配置，Spring Boot 框架是无法自动完成的，需要开发者在 Spring Boot 特定的配置文件中自己完成。
好了，接下来我们就一起来学习用 Spring Boot 启动 Web 应用的具体操作。
1、创建 HelloHandler，具体步骤与 Spring MVC 一样

@RestController
public class HelloHandler {
  @GetMapping("/index")
  public String index() {
    return "Hello World";
  }
}

2、创建 Spring Boot 启动类 Application

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class,args);
  }
}

这个类是整个 Spring Boot 应用的入口，可以看到在类定义处添加了一个 @SpringBootApplication 注解，这个注解是 Spring Boot 的核心，它开启了 Spring Boot 的自动化配置，开发者可以使用自定义配置来覆盖自动化配置，同时它完成了自动扫包，默认的范围是该类所在包的所有子包，当然也包括所在包本身，因此我们在实际开发中应该将启动类放在跟目录下。
3、启动 Spring Boot 应用，直接运行启动类的 main 方法即可，会自动将项目部署到内置 Tomcat 中，并启动 Tomcat启动成功，并且默认端口为 8080，控制台运行curl http://localhost:8080/index，打印Hello World。应用启动后会自动进行扫描，将需要的类交给 Spring IoC 容器来管理，被扫描的类需要在 Application 同级或子级包下。

02-Spring Boot 启动原理
Spring Boot 自动配置原理
Spring Boot 的核心功能是自动配置，意思就是 Spring Boot 框架可以自动读取各种配置文件，并将项目所需要的组件全部加载到 IoC 容器中，包括开发者自定义组件（如 Controller、Service、Repository）以及框架自带组件。
那么 Spring Boot 是如何做到自动配置的呢？要探究底层原理，我们应该从哪里入手呢？入口就是@SpringBootApplication 注解。
@SpringBootApplication 注解实际是由 3 个注解组合而来的，它们分别是：

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
如下所示，两种配置方式结果是一样的。
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class,args);
  }
}

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class,args);
  }
}

要搞懂 Spring Boot 自动配置原理，只需要搞清楚这 3 个注解即可，我们分别来学习。

@SpringBootConfiguration
该注解其实就是 @Configuration，@Configuration 是 Spring Boot 中使用率非常高的一个注解，作用是标注一个配置类，用 Java 类的形式来替代 XML 的配置方式
@SpringBootConfiguration 注解在这里的作用是将启动类 Application 标注成为一个配置类

传统的 XML 配置 bean 的方式
@Data
@AllArgsConstructor
public class Account {    
  private String username;    
  private String password;
}

<beans>
  <bean id="accout" class="com.southwind.Account">
    <property name="username" value="root"></property>
    <property name="password" value="root"></property>
  </bean>
</beans>

使用 @Configuration 注解的方式
@Configuration
public class MyConfiguration {
  @Bean(name = "accout")    
  public Account getAccount(){        
    return new Account("root","root");    
  }
}

上述两种方式的结果是一样的，都是在 IoC 容器中注入了一个 Account Bean。

@ComponentScan
该注解的作用是自动扫描并加载符合条件的组件，通过设置 basePackages 参数的值来指定需要扫描的根目录，该目录下的类及其子目录下的类都会被扫描，默认值为添加了该注解的类所在的包

启动类添加了该注解，那么默认的扫描根目录就是启动类所在的包。如，启动类 Application 所在的包是 com.southwind.springboottest，config、controller、repository 3 个包都是 com.southwind.springboottest 的子包，所以这些类的实例都会被注入到 IoC 容器中。

现在给 @ComponentScan 设置 basePackages 参数，修改代码如下所示。

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.southwind.springboottest.controller","com.southwind.springboottest.repository"})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

此时的配置意味着只扫描 com.southwind.springboottest.controller 和 com.southwind.springboottest.repository 这两个包，那么只有这两个包中类的实例会被注入到 IoC 容器中。
@ComponentScan常用参数如下所示：

basePackages：指定需要扫描的根包目录
basePackageClasses：指定需要扫描的根类目录
lazyInit：是否开启惰性加载，默认关闭
useDefaultFilters：是否启用自动扫描组件，默认 true
excludeFilters：指定不需要扫描的组件类型
includeFilters：指定需要扫描的组件类型
@EnableAutoConfiguration

该注解是自动配置的核心，非常重要，结合 @Import 注解，将依赖包中相关的 bean 进行注册
@EnableAutoConfiguration 是由两个注解组合而成，分别是

@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
1、@AutoConfigurationPackage 注解的作用是自动配置框架自带组件，该注解其实也是使用了 @Import({Registrar.class})

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Registrar.class})
public @interface AutoConfigurationPackage {
}

@Import 注解的作用是根据其参数类所返回的信息，将对应的 bean 进行注册，比如这里的参数类是 Registrar.class，Registrar.class 返回信息如下所示：

static class Registrar implements ImportBeanDefinitionRegistrar, DeterminableImports {
  Registrar() {
  }

  public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
    AutoConfigurationPackages.register(registry, new String[]{(new AutoConfigurationPackages.PackageImport(metadata)).getPackageName()});
  }

  public Set<Object> determineImports(AnnotationMetadata metadata) {
    return Collections.singleton(new AutoConfigurationPackages.PackageImport(metadata));
  }
}

类中方法的作用是将启动类 Application 所在的包下的所有组件注册到 IoC 容器中，即 SpringBoot 默认会扫描启动类所在的包下的所有组件。
2、@Import({AutoConfigurationImportSelector.class}) 完成注册开发者自定义组件，即AutoConfigurationImportSelector.class 返回信息就是框架默认加载的组件，打开AutoConfigurationImportSelector.class 源码如下所示：

public class AutoConfigurationImportSelector implements DeferredImportSelector, BeanClassLoaderAware, ResourceLoaderAware, BeanFactoryAware, EnvironmentAware, Ordered {
    protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
    List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
    Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
    return configurations;
  }
}

核心代码的是调用 SpringFactoriesLoader.loadFactoryNames 方法从依赖的 jar 包中读取 META-INF/spring.factories 文件中的信息，如下所示。

# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.cloud.CloudServiceConnectorsAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration

该文件中存在一组 key = org.springframework.boot.autoconfigure.EnableAutoConfiguration 的配置信息，就是要自动配置的 bean，并且对应的都是 @Configuration 类，Spring Boot 通过加载这些配置类，将需要的组件加载到 IoC 容器中。

03-微服务概述
为什么要使用微服务？
传统的 Java Web 都是采用单体架构的方式来进行开发、部署、运维的，所谓单体架构就是将 Application 的所有业务模块全部打包在一个文件中进行部署。但是随着互联网的发展、用户数量的激增、业务的极速扩展，传统的单体应用方式的缺点就逐渐显现出来了，给开发、部署、运维都带来了极大的难度，工作量会越来越大，难度越来越高。
因为在单体架构中，所有的业务模块的耦合性太高，耦合性过高的同时项目体量又很大势必会给各个技术环节带来挑战。项目越进行到后期，这种难度越大，只要有改动，整个应用都需要重新测试，部署，极大的限制了开发的灵活性，降低了开发效率。同时也带来了更大的安全隐患，如果某个模块发生故障无法正常运行就有可能导致整个项目崩溃，单体应用的架构如下图所示。


单体应用存在的问题

随着业务的发展，开发变得越来越复杂。
修改、新增某个功能，需要对整个系统进行测试，重新部署。
一个模块出现问题，很可能导致整个系统崩溃。
多团队同时对数据进行管理，容易产生安全漏洞。
各模块使用同一种技术框架，很难根据具体业务需求选择更合适的框架，局限性太大。
模块内容太复杂，如果员工离职，可能需要很长时间才能完成任务交接。
为了解决上述问题，微服务架构应运而生，简单来说，微服务就是将一个单体应用拆分成若干个小型的服务，协同完成系统功能的一种架构模式，在系统架构层面进行解耦合，将一个复杂问题拆分成若干个简单问题。这样的好处是对于每一个简单问题，开发、维护、部署的难度就降低了很多，可以实现自治，可以自主选择最合适的技术框架，提高了项目开发的灵活性。
微服务架构不仅是单纯的拆分，拆分之后的各个微服务之间还要进行通信，否则就无法协同完成需求，也就失去了拆分的意义。不同的微服务之间可以通过某种协议进行通信，相互调用、协同完成功能，并且各服务之间只需要制定统一的协议即可，至于每个微服务是用什么技术框架来实现的，统统不需要关心。这种松耦合的方式使得开发、部署都变得更加灵活，同时系统更容易扩展，降低了开发、运维的难度，单体应用拆分之后的架构如下图所示。

微服务的优点
各个服务之间实现了松耦合，彼此之间不需要关注对方是用什么语言，什么技术开发的，只需要保证自己的接口可以正常访问，通过标准协议可以访问其他微服务的接口即可。
各个微服务之间是独立自治的，只需要专注于做好自己的业务，开发和维护不会影响到其他的微服务，这和单体架构中"牵一发而动全身"相比是有很大优势的。
微服务是一个去中心化的架构方式，相当于用零件去拼接一台机器，如果某个零件出现问题，可以随时进行替换从而保证机器的正常运转，微服务就相当于零件，整个项目就相当于零件组成的机器。
任何一种架构方式都有其优势，同时也一定会存在不足，我们需要做的是根据具体的业务场景，选择最合适的架构来进行开发。
微服务的不足
各个服务之间是通过远程调用的方式来完成协作任务的，如果因为某些原因导致远程调用出现问题，导致微服务不可用，就有可能产生级联反应，造成整个系统崩溃。
如果某个需求需要调用多个微服务，如何来保证数据的一致性是一个比较大的问题，这就给给分布式事务处理带来了挑战。
相比较于单体应用开发，微服务的学习难度会增加，对于加入团队的新员工来讲，如何快速掌握上手微服务架构，是他需要面对的问题。
即便是微服务存在这样那样的问题，也不能掩盖这种技术选型在当今互联网环境下的巨大优势，了解完微服务的基本概念、优缺点之后，我们来谈一谈微服务的设计原则。
我们说过微服务就是对应用进行拆分，在软件设计层面进行解耦合，说起来容易做起来难，如何来拆分服务也是一个有难度的挑战，如果拆分的服务粒度太小，导致微服务可完成的功能有限，这种情况反而会增加系统的复杂度。相反如果服务粒度太大，又没有实现足够程度的解耦合，那它本质上就还是单体应用的架构，同样是失去了拆分服务的意义。比较合理的拆分方式是由大到小，提炼出核心需求，搞清楚服务间的交互关系，先拆分成粒度相对较大的服务，然后再根据具体的业务需求逐步细化服务粒度，最终形成一套合理的微服务体系。
微服务设计原则
服务粒度不能过大也不能过小，提炼核心需求，根据服务间的交互关系找到最合理的服务粒度。
各个微服务的功能职责尽量单一，避免出现多个服务处理同一个需求。
各个微服务之间要相互独立、自治，自主开发、自主部署、自主维护。
保证数据独立性，各个微服务独立管理其业务模块下的数据，可开放出接口供其他微服务访问数据，但是其他微服务不能直接管理这些数据。
使用 REST 协议完成微服务之间的协作任务，数据交互采用 JSON 格式，方便调用与整合。
综上所述，微服务的架构设计不是一次成型的，需要反复进行实践和总结，不断进行优化，最终形成一套更为合理的解决方案，微服务架构图如下所示。


微服务架构的核心组件
服务治理（服务注册、服务发现）
拆分之后的微服务首先需要完成的工作就是实现服务治理，包括服务注册和服务发现。这里我们把微服务分为两类：提供服务的叫做服务提供者，调用服务的叫做服务消费者。一个服务消费者首先需要知道有哪些可供调用的微服务，以及如何来调用这些微服务。所以就需要将所有的服务提供者在注册中心完成注册，记录服务信息，如 IP 地址、端口等，然后服务消费者可以通过服务发现获取服务提供者的这些信息，从而实现调用。
服务负载均衡
微服务间的负载均衡是必须要考虑的，服务消费者在调用服务提供者的接口时，可根据配置选择某种负载均衡算法，从服务提供者列表中选择具体要调用的实例，从而实现服务消费者与服务提供者之间的负载均衡。
微服务容错
前面我们提到过，各个服务之间是通过远程调用的方式来完成协作任务的，如果因为某些原因使得远程调用出现问题，导致微服务不可用，就有可能产生级联反应，造成整个系统崩溃。这个问题我们可以使用微服务的熔断器来处理，熔断器可以防止整个系统因某个微服务调用失败而产生级联反应导致系统崩溃的情况。
分布式配置
每个微服务都有其对应的配置文件，在一个大型项目中管理这些配置文件也是工作量很大的一件事情，为了提高效率、便于管理，我们可以对各个微服务的配置文件进行集中统一管理，将配置文件集中保存到本地系统或者 Git 仓库，再由各个微服务读取自己的配置文件。
服务监控
一个分布式系统中往往会部署很多个微服务，这些服务彼此之间会相互调用，整个过程就会较为复杂，我们在进行问题排查或者优化的时候工作量就会比较大。如果我们能准确跟踪到每一个网络请求，了解它整个运行流程，经过了哪些微服务、是否有延迟、耗费时间等，这样的话我们分析系统性能，排查解决问题就会容易很多，这就是服务监控。
Spring Cloud
微服务是一种分布式软件架构设计方式，具体的落地框架有很多，如阿里的 Dubbo、Google 的 gRPC、新浪的 Motan、Apache 的 Thrift 等，都是基于微服务实现分布式架构的技术框架，本课程我们选择的框架的是 Spring Cloud，Spring Cloud 基于 Spring Boot 使得整体的开发、配置、部署都非常方便，可快速搭建基于微服务的分布式应用，Spring Cloud 相当于微服务各组件的集大成者，下图来自 Spring 官网。


Spring Boot 和 Spring Cloud 的关系可大致理解为，Spring Boot 快速搭建基础系统，Spring Cloud 在此基础上实现分布式系统中的公共组件，如服务注册、服务发现、配置管理、熔断器、控制总线等，服务调用方式是基于 REST API，整合了各种成熟的产品和架构。

对于 Java 开发者而言，Spring 框架已成为事实上的行业标准，Spring 全家桶系列产品的优势在于功能齐全、简单好用、性能优越、文档规范，实际开发中就各方面综合因素来看，Spring Cloud 是微服务架构中非常优秀的实现方案，本课程就将为各位读者详细解读如何快速上手 Spring Cloud，Spring Cloud 的核心组件如下图所示，我们会按照图中分布来逐一讲解各个组件的使用，最后会结合一个实战案例将各个组件进行串联，让读者可以将 Spring Cloud 运用在实际开发中。

原文链接：https://blog.csdn.net/qq_44774200/article/details/121324523