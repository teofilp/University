import socket, select, pickle

server_address = ('127.0.0.1', 7000)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.bind(server_address)
    server.setblocking(False)
    server.listen()
    inputs = [server]
    outputs = []
    queue = {}

    while inputs:
        readable, writable, ex = select.select(inputs, outputs, inputs)
        for s in readable:
            if s == server:
                # new client available
                client, addr = s.accept()
                # client.setblocking(False)
                inputs.append(client)
            else:
                # client sent data
                data = s.recv(1000)
                if data:
                    items = pickle.loads(data)
                    print(items)
                    total = sum(items)
                    queue[s] = total
                    if s not in outputs:
                        outputs.append(s)
                else:
                    if s in inputs:
                        inputs.remove(s)
                    if s in outputs:
                        outputs.remove(s)
                    if s in queue:
                        del queue[s]
                    s.close()

        for s in writable:
            if s in queue:
                s.send(pickle.dumps(queue[s]))
                del queue[s]