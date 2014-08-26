#!/bin/bash
# This will start/stop house monitoring tool.
# To run go: ./housemonitoring.sh start/stop

#this is needed to run script as root, otherwise tomcat7 can't execute it
#sudo su <<HERE

function start(){
	echo "starting house monitoring process"
	path="/leo/git/RpiRepo/RpiJavaProject/bin/"
	currentlogfile=$(sudo ls -t $path | head -1)
	echo $path$currentlogfile
	line=$(head -n 1 $path$currentlogfile)
	index=$(echo $line | grep -b -o @ | cut -d: -f1)
	echo $index
	PID=${line:0:$index}
	echo $PID
	if ps -p $PID > /dev/null; then
   		echo "$PID is running, no need to start"
   	else
   		echo "no $PID running now - gonna start"	
   		cd /leo/git/RpiRepo/RpiJavaProject/bin
		sudo java -cp .:../lib/java-mail-1.4.jar cam.HouseConstructionMonitor &
	fi
}

function stop(){
	echo "killing house monitoring process"
	path="/leo/git/RpiRepo/RpiJavaProject/bin/"
	currentlogfile=$(sudo ls -t $path | head -1)
	echo $path$currentlogfile
	line=$(head -n 1 $path$currentlogfile)
	index=$(echo $line | grep -b -o @ | cut -d: -f1)
	echo $index
	PID=${line:0:$index}
	echo $PID
	if ps -p $PID > /dev/null; then
   		echo "$PID is running"
   		sudo kill -9 $PID
   	else
   		echo "no $PID running now"	
	fi		

}

if [ $1 == "start" ]; then
	echo "start it"
	start
fi

if [ $1 == "stop" ]; then
	echo "stop it"
	stop
fi

exit

#HERE
