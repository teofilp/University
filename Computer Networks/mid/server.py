import socket, random, pickle
from threading import Thread, Lock

def get_digits_number():
    global secret_number
    copy_number = secret_number
    n = 0
    while copy_number > 0:
        n += 1
        copy_number //= 10

    return n

def get_digits():
    global secret_number
    number_digits = []
    copy_secret = secret_number
    while copy_secret > 0:
        number_digits.append(copy_secret % 10)
        copy_secret //= 10

    number_digits.reverse()

    return number_digits

def get_matching_digits(guess):
    global digits
    matched = []

    for i in range(len(digits)):
        if digits[i] == guess:
            matched.append(i)

    return matched

server_address = ('127.0.0.1', 7000)

secret_number = random.randint(1, 10000)
digits_number = get_digits_number()
digits = get_digits()
active_threads = []
end = False
lock = Lock()

print(digits)

def handle_user(conn, addr):
    global digits_number, digits, end, lock
    guessed_digits = [0] * digits_number
    
    conn.send(pickle.dumps(digits_number))

    while len([x for x in guessed_digits if x == 0]) > 0:
        guess = pickle.loads(conn.recv(1000))
        matched = get_matching_digits(guess)

        for i in matched:
            guessed_digits[i] = 1
        
        conn.send(pickle.dumps(matched))

        lock.acquire()
        if end:
            lock.release()
            break
        lock.release()    

    winner = len([x for x in guessed_digits if x == 0]) == 0
    end = True

    udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    message = ''
    if winner:
        message = 'You won!' 
    else: 
        message = 'You lost!'

    udp_socket.sendto(pickle.dumps(message), addr)
    

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.bind(server_address)
    server.listen()
    server.settimeout(2)

    while not end:
        try:
            conn, addr = server.accept()

            t = Thread(target=handle_user, args=(conn,addr))
            t.start()
            active_threads.append(t)
        except socket.timeout:
            pass
    for t in active_threads:
        t.join()
    
