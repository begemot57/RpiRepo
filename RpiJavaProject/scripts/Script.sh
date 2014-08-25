#!/bin/bash
line=$(head -n 1 Configuration.conf)
echo echo $line | grep -b -o @ | cut -d: -f1
index=$(echo $line | grep -b -o @ | cut -d: -f1)
echo $index
echo ${line:0:4}
