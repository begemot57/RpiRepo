#!/bin/bash
# This will remove old pic.jpg, start cam, and start mjpg_streamer server
# Access this URL to see the stream: http://begemot57.ddns.net:8090/stream.html
# To run go: ./cam_and_mjpg_streamer.sh start/stop

#this is needed to run script as root, otherwise tomcat7 can't execute it
sudo su <<HERE

#cd /leo/git/RpiRepo/RpiJavaProject/bin
#java -cp .:../lib/java-mail-1.4.jar cam.HouseConstructionMonitor &

line=$(head -n 1 Configuration.conf)
index=$(echo $line | grep -b -o @ | cut -d: -f1)
echo $index
echo ${line:0:$index}

exit

HERE
