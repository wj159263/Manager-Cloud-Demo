#把Dockerfile和docker-compose.yml复制到linux下执行
#首先的把项目打包，并放到和Dockerfile同一个文件夹下
FROM openjdk:8-jre   #依赖jdk
RUN mkdir -p /usr/local/manager/netconfig/  #在docker容器创建目录
COPY manager-config-8888.jar /usr/local/manager/netconfig/  #把弄好的jar包从宿主机复制到上一步创建的容器目录中
CMD java -jar /usr/local/manager/netconfig/manager-config-8888.jar --spring.profiles.active=prod  #运行jar包，指定为开发环境
EXPOSE 8888

#执行命令: docker build -t manager-config-8888 .
#之后docker-compose