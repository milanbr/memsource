FROM openjdk:11.0-jdk-oracle

EXPOSE 8001

ENV DB_HOST=host.docker.internal
ENV DB_PORT=5432
ENV DB_DB=memsource
ENV DB_USERNAME=memsource
ENV DB_PASSWORD=memsource
ENV ENVIRONMENT=dev

ADD build/libs/memsource-service.jar memsource-service.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ENVIRONMENT}", "memsource-service.jar" ]
