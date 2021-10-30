#!/bin/bash

for file in $(find dir -type l); do
	if [ ! -e $file ]; then
		echo $file
	fi 
done
