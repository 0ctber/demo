#这里放java要执行的mainclass, 例: java appName
appName=`cat MainClass`


#查找进程之前是否已存在
appId=$(ps -ef|grep java|grep "${appName}"|awk '{print $2}')

status(){
  echo "java process pid: $appId, appName : $appName"
}

stop(){
if [ ! $appId  ]; then
        echo "can not find pid $appName"
else
        echo "kill java pid: $appId"
        sudo kill -9 $appId
        echo "kill done"
        echo "" > pid_${appName}
fi
}

start(){
appId=$(jcmd | grep ${appName}|awk '{print $1}' )
if [ $appId  ]; then
	echo "java process pid: $appId, appName : $appName, exists, pls confirm and kill first by manual"
	exit 1
fi

java -cp "conf/:lib/*:*" $(echo ${appName}) > std.log 2>&1 &
echo $appName' pid is : '$!
echo $! > pid_${appName}
}

restart(){
	stop
	start
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh run.sh [start|stop|restart|status]"
    exit 1
}

