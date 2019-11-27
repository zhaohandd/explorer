#!/usr/bin/env bash
JAR_NAME="explorer-run-liver-1.0-SNAPSHOT.jar"
pid=`ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}'`
kill -9 ${pid}
echo "kill the pid..."
echo "start the service..."
java -jar ${JAR_NAME}