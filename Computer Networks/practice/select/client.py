import socket, pickle

server_address = ('127.0.0.1', 7000)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect(server_address)
    print('connected')
    s.send(pickle.dumps([1, 2]))
    data = pickle.loads(s.recv(1000))
    s.close()
    print(data)