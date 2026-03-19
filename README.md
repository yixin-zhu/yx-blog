# Yixin Blog: a SpringBootBlog 

这是一个基于 Spring Boot 2.5 + MyBatis-Plus + Vue 构建的前后端分离的博客系统。包含博客前台展示系统和后台管理系统。

## 🚀 模块说明

该项目采用 Maven 多模块架构，主要分为以下三个模块：

- **yixin-blog**：博客前台系统 API，提供文章展示、评论、分类标签、友链等功能。
- **yx-admin**：博客后台管理系统 API，提供文章管理、用户管理、角色权限控制、分类标签管理、系统日志等功能。
- **yx-framework**：核心公共框架模块，包含实体类、数据访问层、业务逻辑层、通用工具类、全局异常处理、安全配置等核心代码。

## 🛠️ 技术栈

### 后端技术
- **核心框架**：Spring Boot 2.5.0
- **持久层框架**：MyBatis-Plus 3.4.3
- **安全框架**：Spring Security + JWT
- **数据库**：MySQL 8.x
- **缓存**：Redis (Spring Data Redis)
- **对象存储**：阿里云 OSS (Aliyun SDK OSS)
- **JSON处理**：Fastjson
- **接口文档**：Swagger2 (Springfox)
- **Excel导出**：EasyExcel 3.0.5

## 核心功能

1. **文章管理**：文章的发布、编辑、删除、浏览量统计（定时任务）。
2. **分类与标签**：文章的分类划分与多标签支持。
3. **用户与权限管理**：基于 Spring Security 的 RBAC（基于角色的访问控制），包含用户、角色、菜单动态权限。
4. **评论系统**：支持文章评论。
5. **文件上传**：整合阿里云 OSS，支持图片等文件的云端存储。
6. **系统日志**：基于 AOP 自定义的系统操作日志记录（`@mySystemlog`）。
7. **数据导出**：支持将分类等数据通过 EasyExcel 导出为 Excel 文件。
8. **其他功能**：友链管理、前台用户登录/注册等。

## 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 5.7/8.0+
- Redis 5.0+

## 快速启动

### 1. 数据库准备
1. 创建 MySQL 数据库，名称为 `yx_blog`。
2. 导入项目所需的 SQL 文件（如果项目中有提供，请导入至 `yx_blog` 库中）。
3. 修改配置文件中的数据库连接信息：
   - 前台：`yixin-blog/src/main/resources/application.yml`
   - 后台：`yx-admin/src/main/resources/application.yml`
   修改 `spring.datasource.username` 和 `password` 为你的数据库账号密码。

### 2. Redis 准备
确保本地或服务器已启动 Redis 服务（默认端口 6379）。若有密码，请在配置文件中添加 `spring.redis.password`。

### 3. 阿里云 OSS 配置（可选）
如果需要使用图片上传功能，请在两个 `application.yml` 中修改 OSS 配置：
```yaml
myoss:
  xxaccessKey: 你的AccessKey
  xxsecretKey: 你的SecretKey
  xxbucket: 你的BucketName
```

### 4. 启动项目
- **启动前台接口服务**：运行 `yixin-blog` 模块下的 `YixinBlogApplication.java`。默认端口为 `7777`。
- **启动后台管理服务**：运行 `yx-admin` 模块下的 `BlogAdminApplication.java`。默认端口为 `8989`。

### 5. 访问接口文档
启动成功后，可以访问 Swagger 接口文档：
- 前台接口：`http://localhost:7777/swagger-ui.html`
- 后台接口：`http://localhost:8989/swagger-ui.html`

## 📂 目录结构

```text
SpringBootBlog
├── yixin-blog                // 前台 API 模块
│   └── src/main/java/org/example
│       ├── config            // 前台专属配置（Swagger等）
│       ├── controller        // 前台控制器
│       ├── cronjob           // 定时任务（如更新浏览量）
│       ├── filter            // JWT 过滤器
│       ├── runner            // 启动运行器（初始化数据到Redis等）
│       └── YixinBlogApplication.java
├── yx-admin                  // 后台 API 模块
│   └── src/main/java/org/example
│       ├── config            // 后台专属配置
│       ├── controller        // 后台控制器
│       ├── filter            // JWT 过滤器
│       └── BlogAdminApplication.java
└── yx-framework              // 核心公共框架
    └── src/main/java/org/example
        ├── annotation        // 自定义注解
        ├── aspect            // AOP切面
        ├── config            // 公共配置类（Redis, MyBatis-Plus, Web等）
        ├── constants         // 全局常量
        ├── domain            // 实体类
        ├── dto               // 数据传输对象
        ├── enums             // 枚举类
        ├── exception         // 全局异常处理
        ├── handler           // 处理器（安全认证、MyBatisPlus自动填充等）
        ├── mapper            // MyBatis Mapper 接口
        ├── service           // 业务逻辑层接口及实现类
        ├── utils             // 工具类
        └── vo                // 视图对象
```

## 许可证

MIT License
