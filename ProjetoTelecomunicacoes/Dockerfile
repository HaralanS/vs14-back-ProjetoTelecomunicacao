FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/target/vs14-back-banco-0.0.1-SNAPSHOT.jar vs14-back-banco.jar
EXPOSE 8080

ENV SPRING_DATABASE_USER="telecomunicacoes_bd_postgres_user"
ENV SPRING_DATABASE_PASSWORD="jYlbO2nBqQHXDG1AI5thZhKrqBp0wFnK"
ENV SPRING_DATABASE_URL="jdbc:postgresql://dpg-cqem3k1u0jms739fa8u0-a.oregon-postgres.render.com/telecomunicacoes_bd_postgres"
ENV SPRING_MAIL_USERNAME=""
ENV SPRING_MAIL_PASSWORD=""

ENTRYPOINT ["java", "-jar", "vs14-back-banco.jar"]