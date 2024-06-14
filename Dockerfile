# Use a imagem oficial do Maven para buildar o projeto
FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie o código-fonte do projeto
COPY src ./src

# Build o projeto
RUN mvn clean package -DskipTests

# Use uma imagem oficial do OpenJDK 17 para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copie o jar do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponha a porta que a aplicação usa
EXPOSE 8080

# Defina as variáveis de ambiente para o MongoDB
ENV MONGO_USER=${MONGO_USER}
ENV MONGO_PASSWORD=${MONGO_PASSWORD}

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
