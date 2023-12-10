FROM java:8

# 作者
MAINTAINER feinik

# 时区设置为中国标准时间
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 添加应用
ADD target/back-admin-web.jar /back-admin-web.jar

# 执行命令
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /back-admin-web.jar"]

EXPOSE 10010