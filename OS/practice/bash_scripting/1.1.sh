#!/bin/bash

old_ifs=$IFS
IFS=$'\n'
users=$(cat who.fake | awk '{print $1}')

for user in ${users[@]}; do
	pcs=$(cat ps.fake | grep -E -c "^$user")
	echo "$user $pcs"
done
IFS=$old_ifs
