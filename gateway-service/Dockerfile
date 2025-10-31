# ============================
#  Etapa 1: Build (Maven)
# ============================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Define diretório de trabalho
WORKDIR /app

# Copia apenas o pom.xml primeiro (melhora cache)
COPY pom.xml .

# Baixa dependências para cachear
RUN mvn dependency:go-offline -B

# Copia o código-fonte
COPY src ./src

# Compila o projeto e gera o .jar
RUN mvn clean package -DskipTests

# ============================
#  Etapa 2: Runtime (JDK puro)
# ============================
FROM eclipse-temurin:17-jdk

# Diretório de trabalho
WORKDIR /app

# Copia apenas o JAR gerado do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Perfil ativo = docker
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=docker"]
