import socket
s = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
x = input("mesaj: ")
s.sendto(bytearray(x, 'utf-8'), ("127.0.0.1", 7777))
buff,addr = s.recvfrom(10);
print([x for x in buff])