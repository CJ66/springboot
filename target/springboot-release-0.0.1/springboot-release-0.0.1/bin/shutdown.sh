#!/bin/sh 
# SCRIPT: emp app shutdown script
# AUTHOR: 
# DATE: 2012

USER=`whoami`
PWD=`pwd`

if [ -n "$1" ]; then
        PWD=$1
fi

if [ -n "$2" ]; then
        USER=$2
fi

cd $PWD

#execute app conf script
. $PWD/app.conf

crontab -l 2>/dev/null | grep -v $PWD/startup.sh > $PWD/m2m_cron 

#echo "crontab is removed on $DATE " >> $PWD/app_log.log

if [ -s $PWD/m2m_cron ]; then
	crontab $PWD/m2m_cron
else
	crontab -r 2> /dev/null
fi

rm -f $PWD/m2m_cron

PID=`ps -u $USER  -o args,pid  | fgrep "["$APPID"]" | grep -v grep |  awk '{print $NF}'`

if [ -n "$PID" ]; then
	kill -15 $PID 
	echo "$APPID is shut down sucessfully on $DATE by $USER." | tee -a $PWD/app_log.log
else
	echo "$APPID has been shut down."
fi

