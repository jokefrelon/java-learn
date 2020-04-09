# Spring学习

1. 配置文件

	pom.xml

	~~~xml
	<properties>
	  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	  <java.version>1.8</java.version>
	</properties>
	<!-- 首先配置一下基本的文件编码和Java版本 -->
	
	<dependencies>
	
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-web</artifactId>
	            <version>2.2.2.RELEASE</version>
	        </dependency>
	<!-- 提供一些 mvc，aop 的依赖包，这是玩Spring-boot必备的依赖包 -->
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-parent</artifactId>
	            <version>2.2.2.RELEASE</version>
	            <type>pom</type>
	        </dependency>
	<!--  这个依赖是帮助我们自动选择一些依赖最合适的版本，这个是玩Spring-Boot必备的 -->
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-maven-plugin</artifactId>
	            <version>2.1.7.RELEASE</version>
	        </dependency>
	<!--该插件是将Springboot项目打包为可执行的jar包 -->
	</dependencies>
	~~~

	

2. 建立一个controller类，和启动类

