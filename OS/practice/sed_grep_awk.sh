#!/bin/bash
sed -E "s/[ ]+/ /g" last.fake | cut -d" " -f1,3,4 | grep -E "economica.*Sun$" | cut -d" " -f1 | sort | uniq
