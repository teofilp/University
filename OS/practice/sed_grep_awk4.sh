cat ps.fake | grep -E "^root" | sed -E "s/[ ]+/ /g" | cut -d " " -f6 | sort | uniq
