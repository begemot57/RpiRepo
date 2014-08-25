#!/bin/bash
cd /leo/git/RpiRepo/RpiJavaProject/bin &
currentlogfile=$(ls -t *.log | head -1)
echo $currentlogfile
line=$(head -n 1 Configuration.conf)
index=$(echo $line | grep -b -o @ | cut -d: -f1)
echo $index
echo ${line:0:$index}
