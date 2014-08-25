#!/bin/bash
OLDIFS=$IFS
IFS="/"
while read f1 f2
do
	echo "process id : $f1"
	echo "host : $f2"
done < file
IFS=$OLDIFS
