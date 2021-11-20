import socket, pickle

host = '127.0.0.1'
port = 7000
addr = (host, port)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect(addr)
    s.send(pickle.dumps([1, 2, 3, 4, 5, 6]))

    total = pickle.loads(s.recv(1000))
    print(total)

    s.close()
