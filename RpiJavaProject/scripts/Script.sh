#!/bin/bash

#sudo su <<HERE

path="/leo/git/RpiRepo/RpiJavaProject/bin/"
currentlogfile=$(cd $path && ls -t * | head -1)
echo $path$currentlogfile
line=$(head -n 1 $path$currentlogfile)
index=$(echo $line | grep -b -o @ | cut -d: -f1)
echo $index
echo ${line:0:$index}
exit

#HERE
