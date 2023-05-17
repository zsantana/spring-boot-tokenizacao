#!/bin/bash
mvn clean package -Dmaven.test.skip=true
docker build --no-cache -t spring-teste-pagto-digital:v1.0.0 .
docker-compose --env-file ./.env up