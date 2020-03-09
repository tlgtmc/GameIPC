#!/usr/bin/env bash

java -jar target/GameIPC-0.0.1-SNAPSHOT.jar &
java -jar target/GameIPC-0.0.1-SNAPSHOT.jar &
read -n 1 -p “-” userinput
exit 1