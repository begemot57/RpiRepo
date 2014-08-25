#!/bin/bash
#sudo su <<HERE
#pwdvar=$(cd /leo/git/RpiRepo/RpiJavaProject/bin && ls -1)
#echo $pwdvar
currentlogfile=$(cd /leo/git/RpiRepo/RpiJavaProject/bin && ls -t * | head -1)
echo $currentlogfile
#line=$(head -n 1 $currentlogfile)
#index=$(echo $line | grep -b -o @ | cut -d: -f1)
#echo $index
#echo ${line:0:$index}
#HERE
