# 基礎映像，選擇適合的 JDK 版本
FROM openjdk:17-jdk-slim

# 設置工作目錄
WORKDIR /app

# 將打包好的 JAR 文件複製到映像中
COPY target/springboot-mall-0.0.1-SNAPSHOT.jar springboot-mall-0.0.1-SNAPSHOT.jar

# 指定啟動命令
CMD ["java", "-jar", "springboot-mall-0.0.1-SNAPSHOT.jar"]

# 可選：設置運行該應用的默認端口（若應用內指定了端口）
EXPOSE 8080

