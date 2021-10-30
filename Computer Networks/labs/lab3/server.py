def computeDigitsSum(nr):
    if nr == 0:
        return 0
    return nr % 10 + computeDigitsSum(nr//10)

import socket, functools
s = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.bind(("0.0.0.0", 7777))
buff, addr = s.recvfrom(100)
print(buff.decode('utf-8'))
print(addr[1])
s.sendto(bytearray([computeDigitsSum(addr[1])]), addr)