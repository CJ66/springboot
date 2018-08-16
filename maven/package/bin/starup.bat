set JAVA_DEBUG=-Xdebug -Xrunjdwp:transport=dt_socket,address=3999,server=y,suspend=n
#echo off
setlocal ENABLEDELAYEDEXPANSION

for %%i in (../lib/*) do set FILE=!FILE!../lib/%%i;
java -Xmn64m -Xms128m -Xmx512m  -cp  ../config;%FILE% %JAVA_DEBUG% com.emrubik.springboot.SpringBootStartApplication
pause 