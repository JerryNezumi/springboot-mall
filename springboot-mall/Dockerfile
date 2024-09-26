# 第一階段：構建階段
FROM maven:3.8.6-openjdk-17-slim AS build

# 設定工作目錄
WORKDIR /app

# 複製 pom.xml 和源碼到工作目錄
COPY pom.xml .
COPY src ./src

# 執行 Maven 包裝命令，生成 JAR 文件
RUN mvn clean package -DskipTests

# 第二階段：運行階段
FROM openjdk:17-jdk

# 設置工作目錄
WORKDIR /app

# 將打包好的 JAR 文件複製到映像中
COPY --from=build /app/target/springboot-mall-0.0.1-SNAPSHOT.jar ./springboot-mall.jar

# 指定啟動命令
CMD ["java", "-jar", "springboot-mall.jar"]

# 可選：設置運行該應用的默認端口（若應用內指定了端口）
EXPOSE 8080