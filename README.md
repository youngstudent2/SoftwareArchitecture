# 软件体系结构



## 介绍

本仓库是软件体系结构作业仓库，用于提交该课程的作业



## 目录说明

- [StudentManager](./StudentManager)：
  - 第一阶段：学生管理应用（Spring Boot  App），采用MVC的架构模式开发，实现学生信息管理
  - 第二阶段：加入redis缓存，加入服务层，实现容器化
- [StudentManagerGatlingTest](./StudentManagerGatlingTest)：gatling的测试文件
- [HAProxyConfig](./HAProxyConfig)：HAProxy的配置文件



## 阶段描述

### 第一阶段

完成StudentManager

### 第二阶段

通过docker部署：

```
mvn compile jib:dockerBuild --offline
docker run -d --name manager-1-1 --cpus=1 -p 8081:8080 student-manager
docker run -d --name manager-1-2 --cpus=1 -p 8082:8080 student-manager 
docker run -d --name manager-1-3 --cpus=1 -p 8083:8080 student-manager 
docker run -d --name manager-1-4 --cpus=1 -p 8084:8080 student-manager 
```

部署redis：

```
docker run --name redis -p 6379:6379 -e ALLOW_EMPTY_PASSWORD=yes bitnami/redis:latest
```



使用gatling测试：

1. 单服务器的负载性能
2. 四服务器（HAProxy扩展）的负载性能
3. 单服务器+redis服务器的负载性能
4. 四服务器（HAPRoxy扩展）+redis服务器的负载性能



测试的关键代码为：

```scala
val scn = scenario("Scenario Name")
    .exec( 
        http("index_request").get("/")
    )
    .exec(
        http("list_request").get("/students")
    )
    .exec(
        http("detail_request").get("/students/181860058")
    )

setUp(scn.inject(atOnceUsers(500)).protocols(httpProtocol))
```

主要对首页访问、所有查询和id查询进行测试

测试结果为：（看不到图片的话可以尝试用南大校园网访问orz）

**1、单服务器的负载性能**

![single](https://git.nju.edu.cn/youngstudent2/mypics/uploads/d8109333947e2a5d57bacf7ee4aadbac/single.png)

**2、四服务器（HAProxy扩展）的负载性能**

![withhaproxy](https://git.nju.edu.cn/youngstudent2/mypics/uploads/ac3390aa309cab9a0ceff7968356fc0c/withhaproxy.png)

**3、单服务器+redis服务器的负载性能**

![singleredis](https://git.nju.edu.cn/youngstudent2/mypics/uploads/c2dc254cf97d86d872b6df79ac3fc426/singleredis.png)

**4、四服务器（HAPRoxy扩展）+redis服务器的负载性能**

![withhaproxyandredis](https://git.nju.edu.cn/youngstudent2/mypics/uploads/1679a7102001ef2db29504342ddafad2/withhaproxyandredis.png)



对比结果可以看到，性能从小到大为：4<1<3<2

即

- 使用redis的性能弱于不使用redis的
- 水平扩展的性能强于无水平扩展的

前一个的结果似乎不符合预期，但经过分析，主要原因应该是数据库中的数据太少导致查询效率快于向redis服务器获取缓存的效率