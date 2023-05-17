#FROM registry.access.redhat.com/ubi8/openjdk-11:1.14
FROM azul/zulu-openjdk:11.0.9
COPY ./target/*.jar /home/spring/app.jar
USER 1001
EXPOSE 8100
CMD ["sh", "-c", "java -Xms64m -Xmx512M -Xlog:gc -XX:ActiveProcessorCount=2 -jar /home/spring/app.jar"]