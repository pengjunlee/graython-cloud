#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20240629.sql ./mysql/db
cp ../sql/ry_config_20250224.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../graython-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy graython-gateway "
cp ../graython-gateway/target/graython-gateway.jar ./graython/gateway/jar

echo "begin copy graython-auth "
cp ../graython-auth/target/graython-auth.jar ./graython/auth/jar

echo "begin copy graython-visual "
cp ../graython-visual/graython-monitor/target/graython-visual-monitor.jar  ./graython/visual/monitor/jar

echo "begin copy graython-modules-system "
cp ../graython-modules/graython-system/target/graython-modules-system.jar ./graython/modules/system/jar

echo "begin copy graython-modules-file "
cp ../graython-modules/graython-file/target/graython-modules-file.jar ./graython/modules/file/jar

echo "begin copy graython-modules-job "
cp ../graython-modules/graython-job/target/graython-modules-job.jar ./graython/modules/job/jar

echo "begin copy graython-modules-gen "
cp ../graython-modules/graython-gen/target/graython-modules-gen.jar ./graython/modules/gen/jar

