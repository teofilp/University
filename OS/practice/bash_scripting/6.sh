#!/bin/bash

IFS=$'\n'
files=($(find dir -ls | grep -E " ..w..w..w. "))

#echo ${files[@]}

for file in ${files[@]};
do
	fileName=`echo $file | awk '{ print $NF }'` 
	echo $fileName
	chmod go-w $fileName
done
