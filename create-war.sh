#!/bin/sh

source ./setenvs.sh

TARGET_DIST=$1
if [ -z $TARGET_DIST ]; then
  TARGET_DIST=local
fi

mvn clean package -P $TARGET_DIST -DskipTests=true
