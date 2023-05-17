#!/bin/bash
./export.sh
mvn clean package -Dmaven.test.skip=true
