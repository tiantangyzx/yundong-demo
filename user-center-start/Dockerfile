FROM registry.cn-hangzhou.aliyuncs.com/XXXX/yundong-base:1.0.0
EXPOSE 8888

USER admin:admin
ENV TZ 'Asia/Shanghai'
ENV LANG="C.UTF-8"

ARG JAR_FILE
ADD ${JAR_FILE} /home/admin/app.jar

ENTRYPOINT ["java", "-jar", "/home/admin/app.jar"]
