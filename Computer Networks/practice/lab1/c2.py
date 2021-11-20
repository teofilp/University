import socket, pickle

host = '127.0.0.1'
port = 7000
addr = (host, port)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect(addr)
    s.send("ana are 2 mere".encode('utf-8'))

    result = pickle.loads(s.recv(1000))
    print(result)