#首先的把项目打包，并放到和Dockerfile同一个文件夹下
 #依赖jdk
FROM openjdk:8-jre
#在docker容器创建目录
RUN mkdir -p /usr/local/manager/eureka/
#把弄好的jar包从宿主机复制到上一步创建的容器目录中
COPY manager-eureka-7000.jar /usr/local/manager/eureka/
#运行jar包，指定为开发环境
CMD java -jar /usr/local/manager/eureka/manager-eureka-7000.jar --spring.profiles.active=prod
EXPOSE 7000

#执行命令: docker build -t manager-config-8888 .
#之后docker-compose