#!/bin/bash
rm /home/ubuntu/farm_backend/deploy.log
BUILD_JAR=$(ls /home/ubuntu/farm_backend/build/libs/farm-0.0.1-SNAPSHOT.jar)
JAR_NAME=$(basename $BUILD_JAR)
$PROJECT_NAME=farm
echo "> build 파일명: $JAR_NAME" >> /home/ubuntu/farm_backend/deploy.log

echo "> build 파일 복사" >> /home/ubuntu/farm_backend/deploy.log
DEPLOY_PATH=/home/ubuntu/farm_backend/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ubuntu/farm_backend/deploy.log
CURRENT_PID=$(pgrep -fl farm | grep java | awk '{print $1}')

echo "> pid: $CURRENT_PID" >> /home/ubuntu/farm_backend/deploy.log
if [ -z $CURRENT_PID ]; then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ubuntu/farm_backend/deploy.log
else
  echo "> kill -15 $CURRENT_PID" >> /home/ubuntu/farm_backend/deploy.log
  kill -15 $CURRENT_PID
  sleep 20
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME

echo "> DEPLOY_JAR 배포"    >> /home/ubuntu/farm_backend/deploy.log
nohup java -jar $DEPLOY_JAR >> /home/ubuntu/farm_backend/deploy.log 2>/home/ubuntu/farm_backend/deploy_err.log &