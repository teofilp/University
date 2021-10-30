#!/bin/bash

found=0

files=$(find dir -type f)

for file in ${files[@]}; do
	
	if [ -n "$(file $file | grep -E "c program")" ]; then
		lines=$(wc -l $file | awk '{ print $1 }')
		#print $lines
		if [ "$lines" -ge 500 ]; then
			echo $file
			found=$((found+1))
			if [ $found -eq 2 ]; then
				break
			fi
		fi 
	fi
done
