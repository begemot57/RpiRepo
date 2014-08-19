#!/bin/bash
# This will remove old pic.jpg, start cam, and start mjpg_streamer server
# Access this URL to see the stream: http://begemot57.ddns.net:8090/stream.html

function start(){
	echo "delete old pic.jpg"
	rm /leo/cam/picstream/*
	
	echo "start raspistill into /leo/cam/picstream/pic.jpg"
	raspistill --nopreview -w 640 -h 480 -q 5 -o /leo/cam/picstream/pic.jpg -tl 100 -t 9999999 -th 0:0:0 > /dev/null 2>&1 &
	
	echo "start mjpg_streamer"
	LD_LIBRARY_PATH=/usr/local/lib mjpg_streamer -i "input_file.so -f /leo/cam/picstream -n pic.jpg" -o "output_http.so -w /usr/local/www -p 8090" > /dev/null 2>&1 &
}

function stop(){
	echo "killing raspistill"
	pkill raspistill
	
	echo "killing mjpg_streamer"
	pkill mjpg_streamer
	
	echo "delete old pic.jpg"
	rm /leo/cam/picstream/*
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