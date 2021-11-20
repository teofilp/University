import socket, pickle, time, random

udp_ip = '127.0.0.1'
udp_port = 0
server_address = ('127.0.0.1', 7000)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.connect(server_address)
    
    udp_port = server.getsockname()[1]
    udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    udp_socket.bind((udp_ip, udp_port))

    digits = pickle.loads(server.recv(1000))
    matching = [0] * digits

    while len([x for x in matching if x == 0]) > 0:
        guess = random.randint(0, 9)
        print (guess)
        server.send(pickle.dumps(guess))
        try: 
            guessed = pickle.loads(server.recv(1000))
        except:
            break
        for i in range(len(matching)):
            for j in range(len(guessed)):
                if i == guessed[j]:
                    matching[i] = 1
        time.sleep(.5)

    result, addr = udp_socket.recvfrom(1000)
    result = pickle.loads(result)

    print(result)
    udp_socket.close()