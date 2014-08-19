#!/bin/bash
# This will remove old pic.jpg, start cam, and start mjpg_streamer server
# Access this URL to see the stream: http://begemot57.ddns.net:8090/stream.html

echo "delete old pic.jpg"
rm /leo/cam/picstream/*

echo "start saspistill into /leo/cam/picstream/pic.jpg"
raspistill --nopreview -w 640 -h 480 -q 5 -o /leo/cam/picstream/pic.jpg -tl 100 -t 9999999 -th 0:0:0 &

echo "start mjpg_streamer"
LD_LIBRARY_PATH=/usr/local/lib mjpg_streamer -i "input_file.so -f /leo/cam/picstream -n pic.jpg" -o "output_http.so -w /usr/local/www -p 8090" &