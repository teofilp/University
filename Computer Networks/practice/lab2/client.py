import socket
from random import randint
import time 

low = 1
up = 100

server_address = ('127.0.0.1', 7000)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect(server_address)
    ended = False
    while not ended:
        guess = randint(low, up)
        s.send(bytearray(str(guess), 'utf-8'))

        response = s.recv(1000).decode('utf-8')
        print("guess: " + str(guess))
        if response == 'S':
            up = guess - 1
        elif response == 'B':
            low = guess + 1
        else:
            print(response)
            break
        time.sleep(1)
