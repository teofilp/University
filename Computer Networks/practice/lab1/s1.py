import socket, pickle

host = '127.0.0.1'
port = 7000
addr = (host, port)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.bind(addr)
    server.listen()
    connection, address = server.accept()
    data = pickle.loads(connection.recv(1000))

    total = sum(data)

    connection.send(pickle.dumps(total))