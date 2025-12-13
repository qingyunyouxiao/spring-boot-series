# 快速入门 Spring Boot
发布日期：2023年11月16日
主题：关于 Spring Boot 和 Thymeleaf 的十分钟项目。
### 参考文档
如需更多参考，可查阅以下章节：
* [Apache Maven 官方文档](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven 插件参考指南](https://docs.spring.io/spring-boot/3.4.12/maven-plugin)
* [构建 OCI 镜像](https://docs.spring.io/spring-boot/3.4.12/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.12/reference/web/servlet.html)
* [Thymeleaf 模板引擎](https://docs.spring.io/spring-boot/3.4.12/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)

### 实践指南
以下指南将具体演示部分功能的使用方法：
* [构建 RESTful Web 服务](https://spring.io/guides/gs/rest-service/)
* [使用 Spring MVC 开发 Web 内容](https://spring.io/guides/gs/serving-web-content/)
* [基于 Spring 开发 REST 服务](https://spring.io/guides/tutorials/rest/)
* [表单提交处理方案](https://spring.io/guides/gs/handling-form-submission/)

### Maven 父工程配置覆盖
受 Maven 设计机制影响，子工程 POM 文件会**继承**父工程 POM 中的配置元素。
尽管大部分继承的配置都能满足需求，但同时也会引入一些非必要元素，例如 `<license>`（许可证）和 `<developers>`（开发者信息）。
为避免此类情况，本项目的 POM 文件中对这些元素做了**空值覆盖**处理。
若你手动切换至其他父工程，且确实需要继承这些配置，则需删除当前项目 POM 中的空值覆盖代码。
