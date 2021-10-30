#!/bin/bash
old_ifs=$IFS
IFS=$'\n'
attached_ps=($(cat ps.fake | awk -F' ' '{ print $1 }' | grep -E -v '^$' | sort | uniq -c | awk -F' ' '{print $2" "$1}' ))
usrs=($( cat who.fake | awk -F' ' '{ print $1 }'))


for usr in ${usrs[@]}
do
	for proc in ${attached_ps[@]}
	do
		
		if [[ $proc == *$usr* ]]; then
			echo $proc
		fi
	done 
done

IFS=$old_ifs
