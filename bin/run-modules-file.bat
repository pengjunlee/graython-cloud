@echo off
echo.
echo [???] ???Jar????????Modules-File?????
echo.

cd %~dp0
cd ../graython-modules/graython-file/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 %JAVA_OPTS% -jar graython-modules-file.jar

cd bin
pause