import socket, pickle
from threading import Thread
import sys

HOST = '127.0.0.1'
PORT = 7001
UDP_PORT = 0

data = {
    'connected_users': []
}

def send_message(message):
    #print(data['connected_users'])
    for user in data['connected_users']:
        tokens = user.split(':')
        ip = tokens[0]
        port = int(tokens[1])
        with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as udpSocket:
            udpSocket.sendto(bytearray(message, 'utf-8'), (ip, port))

def handle_messages(sock):
    while True:
        message = input("Enter a message: ")
        if message == "QUIT":
            sock.send(bytearray("QUIT", 'utf-8'))
            sock.close()
            sys.exit()
        else:
            send_message(message)

def handle_users_change(sock):
    while True:
        users = pickle.loads(sock.recv(1000))
        new_users = list(filter(lambda x: x not in data['connected_users'], users))

        if len(new_users) > 0:
            print ("user connected: " + new_users[0])

        old_users = list(filter(lambda x: x not in users, data['connected_users']))
        if len (old_users) > 0:
            print("user disconnected: " + old_users[0])
            
        data['connected_users'] = users

def start_udp_server():
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
        s.bind((HOST, UDP_PORT))
        while True:
            message, addr = s.recvfrom(1000)
            print(message.decode('utf-8'))

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    UDP_PORT = s.getsockname()[1]

    data['connected_users'] = pickle.loads(s.recv(1000))
    t = Thread(target=handle_messages, args=(s,))
    t1 = Thread(target=handle_users_change, args=(s,))
    t2 = Thread(target=start_udp_server)
    t1.start()
    t2.start()
    t.start()
    t.join()
    t1.join()
    t2.join()
