#!/bin/bash
cat passwd.fake | cut -d: -f1,3,5 | sed "s/:/,/g" | grep -E "^m" | awk -F "," ' { if ($2 % 7 == 0) print $3 } '
