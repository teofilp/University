#!/bin/bash

old_ifs=$IFS
IFS=$'\n'

c_files=($(find dir | grep -E '.c$'))
found=0
for file in ${c_files[@]}
do
	line_number=$(cat $file | grep -E '^.*$' -c)
	if [ $line_number -ge 500 ]; then
		echo $file
		found=$((found+1))
		if [ $found -eq 2 ]; then
		break
		fi
	fi
done


IFS=$old_ifs
