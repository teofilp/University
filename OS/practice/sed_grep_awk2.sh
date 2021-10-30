#!/bin/bash
sed -E "s/[ ]+/ /g" last.fake | cut -d" " -f1,7 | awk -F " " ' { split($2, a, ":"); if (a[1] >= 23) print $1 } ' | sort | uniq 
