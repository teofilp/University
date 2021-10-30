#!/bin/bash

cat $1 | awk 'BEGIN {
	s = ""	
}
{	
	if (!(s == "")) 
	{ 
		s = s", "$1"@scs.ubbcluj.ro"
	} else {
		s = $1"@scs.ubbcluj.ro "
	}
}
END { print s }'
