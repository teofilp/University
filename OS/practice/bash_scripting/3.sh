#!/bin/bash

old_ifs=$IFS
IFS=$'\n'
files=($(find dir -type f | grep -E '.log$'))
IFS=$old_ifs
for file in ${files[@]}
do
	sorted=`cat $file | sort`
	echo $sorted > $file
done
