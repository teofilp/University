#!/bin/bash

for user in $(cat passwd.fake | awk -F: '{ print $1 }')
do
	#cat last.fake
	echo $user | grep -E "\<$user\>"
	
done

