#!/bin/bash

name=$1
echo $name
old_ifs=$IFS
IFS=$'\n'
while true
do
	for pc in $(cat ps.fake)
	do
		to_kill=$(echo $pc | awk '{ print $2" "$8 }' | grep -E $name | sed -E "s/ .*//")
		# kill -s9 $to_kill
		if [ -n "$to_kill" ]; then
			echo will kill process: $to_kill
		fi
	done
	sleep 5
done
