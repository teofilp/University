import socket
import pickle 
from threading import Thread

HOST = '127.0.0.1'
PORT = 7001

data = {
    'connected_users': []
}

def notify_users():
    #print("notify users")
    #print(len(data['connected_users']))
    for user in data['connected_users']:
        tokens = user['address'].split(':')
        ip = tokens[0]
        port = int(tokens[1])

        message = pickle.dumps(list(map(lambda x: x['address'], data['connected_users'])))
        user['socket'].send(message)

def handle_user(connection, address):
    string_addr = address[0] + ':' + str(address[1])

    while True:
        message = connection.recv(1000).decode('utf-8')
        print(message)
        if message == 'QUIT':
            data['connected_users'] = list(filter(lambda x: x['address'] != string_addr, data['connected_users']))
            notify_users()
            break
        else:
            print(data)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()

    while True:
        conn, address = s.accept()
        ip, port = address

        data['connected_users'].append({
            'socket': conn, 
            'address': ip + ':' + str(port)
        })
        notify_users()

        t = Thread(target=handle_user, args=(conn, address))
        t.start()
        