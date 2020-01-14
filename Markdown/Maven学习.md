# Maven学习

## 1. 安装配置Maven

从 [maven官方网站](https://maven.apache.org/) 下载maven的安装包,然后解压配置环境变量 **maven_home** 

## 2. Maven pom

(Project Object Model)项目对象模型,主要是负责

- 项目依赖
- 插件
- 执行目标
- 项目构建 profile
- 项目版本
- 项目开发者列表
- 相关邮件列表信息

所有 POM 文件都需要 project 元素和三个必需字段：groupId，artifactId，version。

|     节点     |                             描述                             |
| :----------: | :----------------------------------------------------------: |
|   project    |                        工程的根标签。                        |
| modelVersion |                   模型版本需要设置为 4.0。                   |
|   groupId    | 这是工程组的标识。它在一个组织或者项目中通常是唯一的。例如，一个银行组织 com.companyname.project-group 拥有所有的和银行相关的项目。 |
|  artifactId  | 这是工程的标识。它通常是工程的名称。例如，消费者银行。groupId 和 artifactId 一起定义了 artifact 在仓库中的位置。 |
|   version    | 这是工程的版本号。在 artifact 的仓库中，它用来区分不同的版本。例如：`com.company.bank:consumer-banking:1.0 com.company.bank:consumer-banking:1.1` |

------

现如今idea等编辑器都会自动帮我们生成相关参数,我们只需要在编辑器里直接填写即可

[POM 标签大全详解](https://www.runoob.com/maven/maven-pom.html)

## 3. Maven 构建生命周期
![img](https://www.runoob.com/wp-content/uploads/2018/09/7642256-c967b2c1faeba9ce.png)

| 阶段          | 处理     | 描述                                                     |
| :------------ | :------- | :------------------------------------------------------- |
| 验证 validate | 验证项目 | 验证项目是否正确且所有必须信息是可用的                   |
| 编译 compile  | 执行编译 | 源代码编译在此阶段完成                                   |
| 测试 Test     | 测试     | 使用适当的单元测试框架（例如JUnit）运行测试。            |
| 包装 package  | 打包     | 创建JAR/WAR包如在 pom.xml 中定义提及的包                 |
| 检查 verify   | 检查     | 对集成测试的结果进行检查，以保证质量达标                 |
| 安装 install  | 安装     | 安装打包的项目到本地仓库，以供其他项目使用               |
| 部署 deploy   | 部署     | 拷贝最终的工程包到远程仓库中，以共享给其他开发人员和工程 |

为了完成 default 生命周期，这些阶段（包括其他未在上面罗列的生命周期阶段）将被按顺序地执行。

Maven 有以下三个标准的生命周期：

- **clean**：项目清理的处理
- **default(或 build)**：项目部署的处理
- **site**：项目站点文档创建的处理

### 3.1 Clean 生命周期

当我们执行 mvn post-clean 命令时，Maven 调用 clean 生命周期，它包含以下阶段：

- pre-clean：执行一些需要在clean之前完成的工作
- clean：移除所有上一次构建生成的文件
- post-clean：执行一些需要在clean之后立刻完成的工作

### 3.2 Default (Build) 生命周期

这是 Maven 的主要生命周期，被用于构建应用，包括下面的 23 个阶段：

| 生命周期阶段                                | 描述                                                         |
| :------------------------------------------ | :----------------------------------------------------------- |
| validate（校验）                            | 校验项目是否正确并且所有必要的信息可以完成项目的构建过程。   |
| initialize（初始化）                        | 初始化构建状态，比如设置属性值。                             |
| generate-sources（生成源代码）              | 生成包含在编译阶段中的任何源代码。                           |
| process-sources（处理源代码）               | 处理源代码，比如说，过滤任意值。                             |
| generate-resources（生成资源文件）          | 生成将会包含在项目包中的资源文件。                           |
| process-resources （处理资源文件）          | 复制和处理资源到目标目录，为打包阶段最好准备。               |
| compile（编译）                             | 编译项目的源代码。                                           |
| process-classes（处理类文件）               | 处理编译生成的文件，比如说对Java class文件做字节码改善优化。 |
| generate-test-sources（生成测试源代码）     | 生成包含在编译阶段中的任何测试源代码。                       |
| process-test-sources（处理测试源代码）      | 处理测试源代码，比如说，过滤任意值。                         |
| generate-test-resources（生成测试资源文件） | 为测试创建资源文件。                                         |
| process-test-resources（处理测试资源文件）  | 复制和处理测试资源到目标目录。                               |
| test-compile（编译测试源码）                | 编译测试源代码到测试目标目录.                                |
| process-test-classes（处理测试类文件）      | 处理测试源码编译生成的文件。                                 |
| test（测试）                                | 使用合适的单元测试框架运行测试（Juint是其中之一）。          |
| prepare-package（准备打包）                 | 在实际打包之前，执行任何的必要的操作为打包做准备。           |
| package（打包）                             | 将编译后的代码打包成可分发格式的文件，比如JAR、WAR或者EAR文件。 |
| pre-integration-test（集成测试前）          | 在执行集成测试前进行必要的动作。比如说，搭建需要的环境。     |
| integration-test（集成测试）                | 处理和部署项目到可以运行集成测试环境中。                     |
| post-integration-test（集成测试后）         | 在执行集成测试完成后进行必要的动作。比如说，清理集成测试环境。 |
| verify （验证）                             | 运行任意的检查来验证项目包有效且达到质量标准。               |
| install（安装）                             | 安装项目包到本地仓库，这样项目包可以用作其他本地项目的依赖。 |
| deploy（部署）                              | 将最终的项目包复制到远程仓库中与其他开发者和项目共享。       |

有一些与 Maven 生命周期相关的重要概念需要说明：

当一个阶段通过 Maven 命令调用时，例如 mvn compile，只有该阶段之前以及包括该阶段在内的所有阶段会被执行。

不同的 maven 目标将根据打包的类型（JAR / WAR / EAR），被绑定到不同的 Maven 生命周期阶段。

### 3.3 Site 生命周期

Maven Site 插件一般用来创建新的报告文档、部署站点等。

- pre-site：执行一些需要在生成站点文档之前完成的工作
- site：生成项目的站点文档
- post-site： 执行一些需要在生成站点文档之后完成的工作，并且为部署做准备
- site-deploy：将生成的站点文档部署到特定的服务器上



## 4. Maven 常用命令

①mvn clean	清理编译后的目录

②mvn compile	编译main目录

③mvn test-compole	编译test目录

④mvn test	运行test里面代码

⑤mvn package	根据配置文件settings.xml里面的信息,打包

⑥mvn install 把打包后的包部署在服务器上

⑦mvn tomcat run 一键构建



















配置项目的打包方式

~~~xml
<packageing>jar</packageing>
~~~



配置所需要的依赖

~~~xml
<dependencies>
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis</artifactId>
		<!--	<version>1.0.3</version>	不写默认就是最新版 	-->
	</dependency>
</dependencies>
~~~

