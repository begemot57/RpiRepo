#!/bin/bash
# This will start/stop house monitoring tool.
# To run go: ./housemonitoring.sh start/stop

#this is needed to run script as root, otherwise tomcat7 can't execute it
#sudo su <<HERE

function start(){
	echo "Starting house monitoring process"
	path="/leo/git/RpiRepo/RpiJavaProject/bin/"
	currentlogfile=$(sudo ls -t $path *.log | head -1)
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
	echo "Killing house monitoring process"
	path="/leo/git/RpiRepo/RpiJavaProject/bin/"
	currentlogfile=$(sudo ls -t $path *.log | head -1)
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
