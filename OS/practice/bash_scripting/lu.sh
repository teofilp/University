#!/bin/bash

users=""
for record in $(cat last.fake | awk '{print $1"/"$7}'); do
	hour=$(echo $record | awk -F/ '{print $2}' | awk -F: '{ print $1}')
	user=$(echo $record | awk -F/ '{print $1}')
	if [ $hour -ge 23 ] && [ $hour -le 24 ]; then
		users="$user$'\n'$users"
	fi
done

printf "%s" $users | sort | uniq
#rm users.txt
