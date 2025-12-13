# social-network
发布日期：2022年6月8日

主题：使用 Spring Boot 构建的社交网络项目，我已经学习了前三个步骤，这是一些[帮助文档](https://github.com/qingyunyouxiao/spring-boot-series/tree/master/social-network/help)。

本文将介绍使用 Spring Boot 构建后端项目的 9 个步骤，并遵循最佳实践。我将展示主要组件以及需要遵循的最佳实践。

## 项目创建

首先，我使用[Spring Initializr](https://start.spring.io/)来构建我的项目。

这个网站让我能够构建一个具有适当包结构和 Maven（或 Gradle）配置的 Spring Boot 项目。

我可以在网站中添加所有需要的依赖项。我还可以配置 Spring Boot 版本或 Java 版本。

更多详情请查看[这篇](https://sergiolema.dev/2021/02/19/spring-boot-setup/)详细文章。

## 控制器和服务

控制器和服务包含了应用程序的所有逻辑。

控制器负责接收 Web 请求。它们将 JSON 内容解析成对象，读取请求参数，并将输出对象转换回 JSON 内容。

这些服务包含了应用程序的业务逻辑。

服务和控制器都是由 Spring 上下文实例化，并在依赖注入中使用。

将控制器和服务之间的逻辑分离，可以实现分层架构，也称为三层架构。

[您可以在本文](https://sergiolema.dev/2021/02/19/controllers-and-services-with-spring-boot/)中找到有关三层架构实现的更多详细信息。

## 安全

应用程序创建完成并包含所有业务逻辑后，就该进行安全防护了。

首先，我必须在 Spring Security 配置中配置公共路由和受保护路由。

接下来，我必须选择一种身份验证系统。它可以是[JWT](https://sergiolema.dev/2023/03/20/spring-security-6-with-jwt-authentication/)、[Cookie](https://sergiolema.dev/2021/11/01/cookie-based-authentication-with-spring-security/)或[OAuth2](https://sergiolema.dev/2021/11/02/oauth2-authentication-with-spring-security-and-github/)。

这三种身份验证方式需要不同的配置。JWT 和 Cookie 身份验证系统需要 HTTP 过滤器来手动读取和验证每个请求。

这两种方法都将密码存储在个人数据库中。如果我不想管理用户的凭据，可以使用 OAuth2。这样，所有用户的凭据都会委托给像 GitHub 或 Google 这样的第三方系统。

接下来就是有状态应用和无状态应用的区别了。无状态应用的后端不会存储任何关于用户历史记录或会话的信息。而有状态应用则会将用户的会话信息存储在后端。

[但是为了安全地存储用户会话，我需要使用像Redis](https://sergiolema.dev/2022/01/27/store-the-http-session-into-redis/)这样的键值存储系统。

借助 Spring Security，我还可以根据用户的角色来保护我的路由。这称为[授权](https://sergiolema.dev/2022/06/07/roles-authorization/)。

最后，为了进一步提高应用程序的安全性，我可以启用Spring Security 的[CSRF保护。](https://sergiolema.dev/2022/04/06/the-csrf-protection-with-spring-security/)

## 数据库连接

[大多数后端应用程序都需要数据库连接。这可以通过使用JPA](https://sergiolema.dev/2021/02/24/spring-jpa-and-database-connection-with-spring-boot/)配置数据库连接和实体定义来实现。

实体定义反映了数据库结构。JPA 会在启动时验证实体和数据库表是否完全匹配。

要以编程方式创建数据库模式，我可以使用[Liquibase](https://sergiolema.dev/2021/03/02/liquibase-on-maven-with-sql-files/)或 Flyway。这些库会在运行应用程序之前执行一组 SQL 文件。这样可以确保在运行应用程序之前，数据库拥有最新的模式版本。

## 实用库

Spring Boot 项目可以包含很多库。但是，在开始一个新项目时，我总是会包含这两个库：[Lombok 和 Mapstruct](https://sergiolema.dev/2021/02/26/project-lombok-and-mapstruct-with-spring-boot/)。

我添加了 Lombok 来生成所有的构造函数、getter、setter 和其他一些实用方法。Lombok 只需几个注解就能在构建项目时自动生成所有构造函数、getter 和 setter，而无需为每个实体编写数百行代码。

应用程序的最佳实践之一是将数据库实体与控制器返回的对象分开。这就是关注点分离模式。但要实现这种逻辑，我需要一些方法将一个对象的每个字段映射到另一个对象。Mapstruct 正好可以帮我解决这个问题。

我只需要定义一个包含一些方法名称的接口，Mapstruct 就会自动确定同名字段之间的映射关系。如果需要进行一些自定义，我可以添加包含这些详细信息的注解。

## 异常处理

所有应用程序都会出现异常情况，但如何处理这些异常情况才是关键。

默认情况下，如果发生异常，HTTP 客户端会收到包含 500 错误代码的堆栈跟踪信息。然而，这不是我想要的行为。堆栈跟踪信息包含了应用程序的关键信息，例如应用程序的结构。

当应用程序受到 Spring Security 保护时，默认情况下，异常会将用户重定向到登录页面。

通过配置切面和[异常处理程序](https://sergiolema.dev/2021/09/13/exception-handler-with-spring-boot/)，我可以轻松捕获应用程序的所有错误，并返回自定义消息和自定义 HTTP 代码。

## 文件处理

在开发后端时，我经常需要处理文件上传或下载。这可以通过Spring Boot 的[MultipartFile对象来实现。](https://sergiolema.dev/2021/09/15/upload-files-with-spring-boot-and-multipartfile/)

但是，在 Spring Boot 应用中存储文件时，我不能选择应用服务器作为存储系统。我需要选择一个更持久的系统，例如 NFS 或 Blob 存储。

## 测试

测试虽然不是生产应用程序的一部分，但却是应用程序中最重要的组成部分之一。

这些测试是为了确保应用程序的稳定性。它们帮助我确保每个新功能都不会破坏应用程序。

测试分为不同层次。最基本的是[单元测试](https://sergiolema.dev/2021/09/15/tests-in-spring-boot-with-junit-5-and-mockito/)（使用 JUnit 和 Mockit）和[集成测试](https://sergiolema.dev/2021/10/07/integration-tests-in-spring-boot-with-mockmvc-dbunit-and-h2/)（使用 MockMvc、DBUnit 和 H2）。

## 日志

最后，应用发布后，我所能获得的所有反馈都来自应用自身生成的日志。因此，我必须格外重视编写高质量的日志。

我必须特别注意日志的存储位置、日志文件的大小、是否以流式传输、是否会阻塞应用程序等问题。

一种解决方案是使用[Slf4J](https://sergiolema.dev/2022/02/13/slf4j-lockback-and-log4j2/)作为应用程序日志写入接口，然后选择 Logback 或 Log4J2 作为日志记录器实现。

## 结论

开发 Spring Boot 应用是一个漫长的过程。但前面提到的 9 个步骤是确保后端稳定且高质量最常用的方法。
