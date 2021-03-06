#!/bin/bash

if [ "$1" = "cleanup" ]
then
  docker-compose down -v
  mvn clean
  docker image rm juplo/data-jdbc:latest
  exit
fi

if [[
  $(docker image ls -q juplo/data-jdbc:latest) == "" ||
  "$1" = "build"
]]
then
  mvn package || exit
else
  echo "Using image existing images:"
  docker image ls juplo/data-jdbc:latest
fi

docker-compose up -d jdbc

while ! [[ $(http :8080/actuator/health 2>/dev/null | jq -r .status) == "UP" ]];
do
  echo "Waiting for User-Service...";
  sleep 1;
done


docker-compose logs --tail=0 -f jdbc &

for i in `seq 1 20`;
do
  echo peter$i | http :8080/users
  echo uwe$i | http :8080/users
  echo peter$i | http :8080/users
  echo simone$i | http :8080/users
  http DELETE :8080/users/franz$i
  http DELETE :8080/users/simone$i
  echo franz$i | http :8080/users
  echo franz$i | http :8080/users
  echo beate$i | http :8080/users
  http DELETE :8080/users/uwe$i
  sleep 1
done;

docker-compose stop
