#!/bin/bash

#this is needed to run script as root, otherwise tomcat7 can't execute it
sudo su <<HERE

function start(){
	if [ "$(ls -A /leo/cam/picstream)" ]; then
		echo "delete old pic.jpg"
		rm /leo/cam/picstream/*
	fi

	if ! pidof -x raspistill > /dev/null; then
		echo "start raspistill into /leo/cam/picstream/pic.jpg"
		raspistill --nopreview -w 640 -h 480 -q 5 -o /leo/cam/picstream/pic.jpg -tl 100 -t 9999999 -th 0:0:0 > /dev/null 2>&1 &
	else
		echo "raspistill is already running"
	fi
	
	if ! pidof -x mjpg_streamer > /dev/null; then
		echo "start mjpg_streamer"
		LD_LIBRARY_PATH=/usr/local/lib mjpg_streamer -i "input_file.so -f /leo/cam/picstream -n pic.jpg" -o "output_http.so -w /usr/local/www -p 8090" > /dev/null 2>&1 &
	else
		echo "mjpg_streamer already running"
	fi
}

function stop(){
	echo "killing raspistill"
	pkill raspistill
	
	echo "killing mjpg_streamer"
	pkill mjpg_streamer
	
	if [ "$(ls -A /leo/cam/picstream)" ]; then
		echo "delete old pic.jpg"
		rm /leo/cam/picstream/*
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

path="/leo/git/RpiRepo/RpiJavaProject/bin/"
currentlogfile=$(cd $path && ls -t * | head -1)
echo $path$currentlogfile
line=$(head -n 1 $path$currentlogfile)
index=$(echo $line | grep -b -o @ | cut -d: -f1)
echo $index
echo ${line:0:$index}

HERE