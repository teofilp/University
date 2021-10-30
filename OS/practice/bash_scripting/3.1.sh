#!/bin/bash

files=$(find dir -type f -name *.log)

for file in ${files[@]}; do
	if [ -s $file ]; then
		cat $file | sort > $file
	fi
done
