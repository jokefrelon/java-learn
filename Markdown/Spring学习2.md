# Spring学习2

## 1. @Conditional注解

这个注解是一个spring的底层注解,在 **SpringBoot** 里面出现了很多的派生注解

```
@Conditional扩展注解	作用（判断是否满足当前指定条件）
@ConditionalOnJava	系统的java版本是否符合要求
@ConditionalOnBean	容器中存在指定Bean
@ConditionalOnMissingBean	容器中不存在指定Bean
@ConditionalOnExpression	满足SpEL表达式指定
@ConditionalOnClass	系统中有指定的类
@ConditionalOnMissingClass	系统中没有指定的类
@ConditionalOnSingleCandidate	容器中只有一个指定的Bean，或者这个Bean是首选Bean
@ConditionalOnProperty	系统中指定的属性是否有指定的值
@ConditionalOnResource	类路径下是否存在指定资源文件
@ConditionalOnWebApplication	当前是web环境
@ConditionalOnNotWebApplication	当前不是web环境
@ConditionalOnJndi	JNDI存在指定项
```

这些东西大概了解一下就可以啦

**@Conditional** 还是用于服务自动配置の,判断哪些需要

## 2. 自动配置总结

学了这么多!我们都是在介绍这个 **SpringBoot** の自动配置,那我来梳理一下,这些东西都是如何一起工作的

---

- 所有的 **SpringBoot** 应用程序都必须以 **@SpringBootApplication** 的注解开始

- 

- 而 **@SpringBootApplication** 又是由多个注解组合而来的,其中就包括 **@EnableAutoConfiguration** 

- 

- 套娃の时候来了, **@EnableAutoConfiguration** 又用注解导入了一个类**AutoConfigurationImportslfector.class**

- 

- 其中 **AutoConfigurationImportslfector** 的 **slfectImports()** 方法通过**SpringFactoriesLoader.loadFactoryNames()** 方法扫描所有具有 **META-INF/spring.factories** 的jar包

- 

- ```java
  	@Override
    	public String[] slfectImports(AnnotationMetadata annotationMetadata) {
    		if (!isEnabled(annotationMetadata)) {
    			return NO_IMPORTS;
    		}
    		AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader
    				.loadMetadata(this.beanClassLoader);
    		AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(autoConfigurationMetadata,
    				annotationMetadata);
    		return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
    	}
  ```

- 

- 补充一下: **spring.factories** 文件也是一组一组的key=value的形式，其中一个key是 **EnableAutoConfiguration** 类的全类名，而它的value是一个 **xxxxAutoConfiguration** 的类名的列表，这些类名以逗号分隔

---

既然我们向容器里面导入了这么多组件,肯定有一些是我们用不到的组件,那 **SpringBoot** 是如何决定他们的启用与否呢?

其实这都是 **@Conditional** の功劳

下面是 **ServletWebServerFactoryAutoConfiguration** の例子🌰

```java
@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnClass(ServletRequest.class)
@ConditionalOnWebApplication(type = Type.SERVLET)
@EnableConfigurationProperties(ServerProperties.class)
@Import({ ServletWebServerFactoryAutoConfiguration.BeanPostProcessorsRegistrar.class,
		ServletWebServerFactoryConfiguration.EmbeddedTomcat.class,
		ServletWebServerFactoryConfiguration.EmbeddedJetty.class,
		ServletWebServerFactoryConfiguration.EmbeddedUndertow.class })
public class ServletWebServerFactoryAutoConfiguration {
	@Bean
	public ServletWebServerFactoryCustomizer servletWebServerFactoryCustomizer(ServerProperties serverProperties) {
		return new ServletWebServerFactoryCustomizer(serverProperties);
	}
	@Bean
	@ConditionalOnClass(name = "org.apache.catalina.startup.Tomcat")
	public TomcatServletWebServerFactoryCustomizer tomcatServletWebServerFactoryCustomizer(
			ServerProperties serverProperties) {
		return new TomcatServletWebServerFactoryCustomizer(serverProperties);
	}
	@Bean
	@ConditionalOnMissingFilterBean(ForwardedHeaderFilter.class)
	@ConditionalOnProperty(value = "server.forward-headers-strategy", havingValue = "framework")
	public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter() {
		ForwardedHeaderFilter filter = new ForwardedHeaderFilter();
		FilterRegistrationBean<ForwardedHeaderFilter> registration = new FilterRegistrationBean<>(filter);
		registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR);
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registration;
	}
	public static class BeanPostProcessorsRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {
		private ConfigurableListableBeanFactory beanFactory;
		@Override
		public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
			if (beanFactory instanceof ConfigurableListableBeanFactory) {
				this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
			}
		}
		@Override
		public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
				BeanDefinitionRegistry registry) {
			if (this.beanFactory == null) {
				return;
			}
			registerSyntheticBeanIfMissing(registry, "webServerFactoryCustomizerBeanPostProcessor",
					WebServerFactoryCustomizerBeanPostProcessor.class);
			registerSyntheticBeanIfMissing(registry, "errorPageRegistrarBeanPostProcessor",
					ErrorPageRegistrarBeanPostProcessor.class);
		}
		private void registerSyntheticBeanIfMissing(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
			if (ObjectUtils.isEmpty(this.beanFactory.getBeanNamesForType(beanClass, true, false))) {
				RootBeanDefinition beanDefinition = new RootBeanDefinition(beanClass);
				beanDefinition.setSynthetic(true);
				registry.registerBeanDefinition(name, beanDefinition);
			}
		}
	}
}
```

其中有一个 **@EnableConfigurationProperties** 注解,开启配置属性，而它后面的参数是一个 **ServerProperties.class** ，这就是习惯优于配置的最终落地点。

在这个类上，我们看到了一个非常熟悉的注解：@ConfigurationProperties，它的作用就是从配置文件中绑定属性到对应的bean上，而@EnableConfigurationProperties负责导入这个已经绑定了属性的bean到spring容器中（见上面截图）。那么所有其他的和这个类相关的属性都可以在全局配置文件中定义，也就是说，真正“限制”我们可以在全局配置文件中配置哪些属性的类就是这些XxxxProperties类，它与配置文件中定义的prefix关键字开头的一组属性是唯一对应的。

至此，我们大致可以了解。在全局配置的属性如：server.port等，通过@ConfigurationProperties注解，绑定到对应的XxxxProperties配置实体类上封装为一个bean，然后再通过@EnableConfigurationProperties注解导入到Spring容器中。

而诸多的XxxxAutoConfiguration自动配置类，就是Spring容器的JavaConfig形式，作用就是为Spring 容器导入bean，而所有导入的bean所需要的属性都通过xxxxProperties的bean来获得。

简而言之就是

```
Spring Boot启动的时候会通过@EnableAutoConfiguration注解找到META-INF/spring.factories配置文件中的所有自动配置类，并对其进行加载
而这些自动配置类都是以AutoConfiguration结尾来命名的，它实际上就是一个JavaConfig形式的Spring容器配置类
它能通过以Properties结尾命名的类中取得在全局配置文件中配置的属性如：server.port，而XxxxProperties类是通过@ConfigurationProperties注解与全局配置文件中对应的属性进行绑定的。
```



可以看一看 [扶墙老师说](https://afoo.me/posts/2015-07-09-how-spring-boot-works.html) の个人博客,简要了解 **SpringBoot** の相关知识



## 3. 日志

### 3.1 日志の作用?

日志就是帮助我们分析程序的运行状态,以及帮助收集错误

### 3.2 日志的架构

通常是一个抽象接口,然后一个实现类

### 3.3 SpringBoot 与 日志框架

**SpringBoot** の底层是使用 **Spring** 框架,而 **Spring** 又是使用の **JCL** 框架

**SpringBoot** 选择的实现类框架是 **slf4j & logback** , **Springboot** 将其他日志框架转换成 **slf4j** ,就是使用中间包的方式

常见的中间包有 :

```
jcl-over-slf4j
jul-to-slf4j
log4j-over-slf4j
```



### 3.4 slf4j(抽象层) の使用

![pic](http://www.slf4j.org/images/legacy.png)

1. 导入jar包

   ```xml
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-logging</artifactId>
               <version>2.2.4.RELEASE</version>
           </dependency>
   ```

2. 移除日志框架

   如果我们需要调用其他框架,而这个框架的默认依赖日志框架不是我们所需要的,那就放心的把这个默认的日志框架移除,不然后面调用我们自己想要使用的日志框架会报错

3. 导入中间包

   中间包就是防止移除默认的日志框架后,我们需要调用的框架不能正常使用的中继包,它带有默认框架的一些接口,并将他们转向我们需要的日志框架

4. 导入slf4jの其他实现

#### 3.4.1 代码实例

```java
package top.day4_slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class stater {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(stater.class);
        logger.debug("debug_xxx");
        logger.error("error_xxx");
        logger.info("info_xxx");
        logger.trace("trace_xxx");
        logger.warn("warn_xxx");
    }
}
```

```verilog
17:03:48.573 [main] DEBUG top.day4_slf4j.stater - debug_xxx
17:03:48.589 [main] ERROR top.day4_slf4j.stater - error_xxx
17:03:48.589 [main] INFO top.day4_slf4j.stater - info_xxx
17:03:48.589 [main] WARN top.day4_slf4j.stater - warn_xxx
```

可以看到只有 **logger.trace** 没有输出,这是为什么?

```properties
logging.level.top.jokeme=trace
#上面logger.trace没有输出就是没有配置这个造成的
logging.path=./log.txt
#如果不指定就在console输出 <一般只用path,不用file>
logging.file=./log/log.txt
#如果不指定就在console输出
```

日志输出以下项目：

- 日期和时间：毫秒精度，易于排序。
- 日志级别：`ERROR`，`WARN`，`INFO`，`DEBUG`，或`TRACE`。
- 进程ID。
- 一个`---`分离器来区分实际日志消息的开始。
- 线程名称：用方括号括起来（对于控制台输出可能会被截断）。
- 记录器名称：这通常是源类名称（通常缩写）。
- 日志消息。

```verilog
2020-05-31 17:58:03.280  INFO 10676 --- [           main] top.day3_properties.stater               : Starting stater on Frelon with PID 10676 (D:\github\java_learn\project\reSpringBoot\target\classes started by Frelon in D:\github\java_learn\project\reSpringBoot)
2020-05-31 17:58:03.284  INFO 10676 --- [           main] top.day3_properties.stater               : No active profile set, falling back to default profiles: default
2020-05-31 17:58:05.304  INFO 10676 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
```



#### 3.4.2 配置文件

**SpringBoot** の配置文件都在 **application.properties/application.yml** 里面

**SpringBoot** 默认的配置文件在 **org.springframework.boot.spring-boot:x.x.x.RELEASE/spring-boot:x.x.x.RELEASE.jar/org.springframework.boot/logging/**

| 日志框架                | 配置文件名称                                                 |
| :---------------------- | ------------------------------------------------------------ |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml`, or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

如果可能，建议将`-spring`变体用于日志记录配置（例如，`logback-spring.xml`而不是`logback.xml`）。如果使用标准配置位置，Spring将无法完全控制日志初始化。在我们以后の开发中,不要直接调用实现类,而是要用抽象层里面の方法,因为一旦调用具体的实现类,我们需要更换框架的时候就要花费巨大的时间和精力去修改代码了,而抽象方法就不一样了,可以随意的更换框架也不需要繁琐的配置

#### 3.4.3 根据环境加载配置文件

先在日志框架の配置文件里面配置以下内容

```xml
<springProfile name="dev">
    <!-- name 是表示环境 -->
</springProfile>
```

然后再在 **SpringBoot** の配置文件 **application.properties** 激活该配置项

```properties
spring.profiles.active=dev
```

或者在命令行激活

```shell
java xxx xxx --spring.profiles.active=dev
```

参考文档: 

[DocsHome](https://github.com/DocsHome/springboot) 

[SpringBoot官方文档](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/)

日志部分先到这里先结束,好多东西以后遇到了再慢慢学

## 4. SpringBoot & Web开发

1. 创建 **SpringBoot** 应用,添加模块
2. 修改少量配置文件
3. 编写代码
4. 开始运行

回顾一下自动配置

1. **xxxAutoConfiguration** 给容器自动配置组件
2. **xxxProperties** 配置类来封装配置文件内容

### 4.1 SpringBoot & 静态资源

```java
"classpath:/META-INF/resources/"
"classpath:/resources/"
"classpath:/static/"
"classpath:/public/"
"/" <当前项目的 / 路径>
```

这几个是常见的静态资源存放路径

注意: **classpath** 是指我们 coding 时候的 **resources📂** 文件夹 ,例如我的就是 ```webApp\src\main\resources```






