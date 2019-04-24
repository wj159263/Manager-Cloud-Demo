FROM openjdk:8-jre
RUN mkdir -p /usr/local/manager/provider-user-7788/
COPY manager-provider-user-7788.jar /usr/local/manager/provider-user-7788/
CMD java -jar /usr/local/manager/provider-user-7788/manager-config-8888.jar --spring.profiles.active=prod
EXPOSE 7788