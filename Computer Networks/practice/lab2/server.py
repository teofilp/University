import socket
from threading import Thread, Lock
import random

ip = '127.0.0.1'
port = 7000

server_address = (ip, port)

secret_number = random.randint(1, 100)
guessed = False
lock = Lock()

def handle_user(sock):
    global guessed, lock
    tries = 1
    while not guessed:
        message = sock.recv(1000)
        guess = int(message)
        reply = ''
        lock.acquire()

        if guessed:
            sock.send(bytearray("You lost within " + str(tries) + " tries", 'utf-8'))
            lock.release()
            sock.close()
            break

        if guess < secret_number:
            reply = 'B'
        elif guess == secret_number:
            reply = 'You won within ' + str(tries) + ' tries'
            guessed = True
        else:
            reply = 'S'
        
        sock.send(bytearray(reply, 'utf-8'))
        if guessed:
            sock.close()
            lock.release()
            break;
        lock.release()
        tries += 1

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind(server_address)
    s.settimeout(2)
    s.listen()
    active_threads = []
    while True: 
        
        while not guessed:
            try:
                connection, address = s.accept()

                t = Thread(target=handle_user, args=(connection,))
                t.start()
                active_threads.append(t)
            except socket.timeout:
                pass

        for t in active_threads:
            t.join()

        guessed = False
        secret_number = random.randint(1, 100)