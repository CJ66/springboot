#!/bin/sh
# SCRIPT: emp app start script
# AUTHOR: 
# DATE: 2012

source ~/.bashrc
export LANG="zh_CN.UTF-8"
USER=`whoami`
PWD=$(cd `dirname $0`; pwd)

echo "PWD IS $PWD"
cd $PWD

#execute app.conf script
. $PWD/app.conf

JAVA_DEBUG=""
if [ $ISDEBUG -eq "1" ]; then
	JAVA_DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"
fi

libpath=".:"
for path in ../lib/*.jar
do
        libpath=$libpath"$path:"
done

PID=`ps -u $USER  -o args,pid  | fgrep "["$APPID"]" | grep -v grep | awk '{print $NF}'`

if [ -z "$PID" ]; then
	java -D[$APPID] $XMX $XMS $XMN $GC $JAVA_DEBUG -DNID=$1 -Djava.awt.headless=true -cp ../config:$libpath com.emrubik.springboot.SpringBootStartApplication &
	echo "$APPID is started up at $DATE by $USER." | tee -a  $PWD/app_log.log
else
	echo "$APPID has been started."
fi 
tail -100f $PWD/app.conf
