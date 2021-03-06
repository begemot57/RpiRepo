#!/bin/bash
# This will start/stop house monitoring tool.
# To run go: ./housemonitor.sh start/stop/checkstate

#this is needed to run script as root, otherwise tomcat7 can't execute it
#sudo su <<HERE

function start(){
	echo "Starting house monitor process"
	path="/leo/git/RpiRepo/RpiJavaProject/bin/"
	currentlogfile=$(sudo ls -t $path | head -1)
	echo "Get PID from here: "$path$currentlogfile
	line=$(head -n 1 $path$currentlogfile)
	index=$(echo $line | grep -b -o @ | cut -d: -f1)
	PID=${line:0:$index}
	echo "PID is:"$PID
	if ps -p $PID > /dev/null; then
   		echo "Process $PID is running, no need to start"
   	else
   		echo "No process $PID is running now - gonna start new one"	
   		cd /leo/git/RpiRepo/RpiJavaProject/bin
		sudo java -cp .:../lib/java-mail-1.4.jar cam.HouseConstructionMonitor &
	fi
}

function stop(){
	echo "Killing house monitor process"
	path="/leo/git/RpiRepo/RpiJavaProject/bin/"
	currentlogfile=$(sudo ls -t $path | head -1)
	echo "Get PID from here: "$path$currentlogfile
	line=$(head -n 1 $path$currentlogfile)
	index=$(echo $line | grep -b -o @ | cut -d: -f1)
	PID=${line:0:$index}
	echo "PID is:"$PID
	if ps -p $PID > /dev/null; then
   		echo "Process $PID is running - gonna kill it now"
   		sudo kill $PID
   	else
   		echo "No process $PID is running now - nothing to kill"
	fi		
}

function checkstate(){
	path="/leo/git/RpiRepo/RpiJavaProject/bin/"
	currentlogfile=$(sudo ls -t $path | head -1)
	line=$(head -n 1 $path$currentlogfile)
	index=$(echo $line | grep -b -o @ | cut -d: -f1)
	PID=${line:0:$index}
	if ps -p $PID > /dev/null; then
   		echo "running"
   	else
   		echo "stopped"
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

if [ $1 == "checkstate" ]; then
	checkstate
fi

exit

#HERE
