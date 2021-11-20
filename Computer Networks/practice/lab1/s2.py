import socket, pickle

host = '127.0.0.1'
port = 7000
addr = (host, port)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.bind(addr)
    server.listen()
    conn, add = server.accept()

    data = conn.recv(1000).decode('utf-8')
    
    total = 0
    for c in data:
        if c == ' ':
            total += 1

    conn.send(pickle.dumps(total))