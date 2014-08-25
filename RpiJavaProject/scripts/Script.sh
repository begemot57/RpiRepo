#!/bin/bash

#this is needed to run script as root, otherwise tomcat7 can't execute it
sudo su <<HERE

path="/leo/git/RpiRepo/RpiJavaProject/bin/"
currentlogfile=$(cd $path && ls -t * | head -1)
echo $path$currentlogfile
line=$(head -n 1 $path$currentlogfile)
index=$(echo $line | grep -b -o @ | cut -d: -f1)
echo $index
echo ${line:0:$index}

HERE