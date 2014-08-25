#!/bin/bash
line=$(head -n 1 Configuration.conf)
echo $line | grep -b -o @ | cut -d: -f1
echo ${line:0:4}
