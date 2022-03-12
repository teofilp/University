

# import the pygame module, so you can use it
from multiprocessing.dummy import current_process
import pickle,pygame,time
from pygame.locals import *
from random import random, randint
import numpy as np
from timer import Timer

#Creating some colors
BLUE  = (0, 0, 255)
GRAYBLUE = (50,120,120)
RED   = (255, 0, 0)
GREEN = (0, 255, 0)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

#define directions
UP = 0
DOWN = 2
LEFT = 1
RIGHT = 3

#define indexes variations 
v = [[-1, 0], [1, 0], [0, 1], [0, -1]]


class Map():
    def __init__(self, n = 20, m = 20):
        self.n = n
        self.m = m
        self.surface = np.zeros((self.n, self.m))
    
    def randomMap(self, fill = 0.2):
        for i in range(self.n):
            for j in range(self.m):
                if random() <= fill :
                    self.surface[i][j] = 1
                
    def __str__(self):
        string=""
        for i in range(self.n):
            for j in range(self.m):
                string = string + str(int(self.surface[i][j]))
            string = string + "\n"
        return string
                
    def saveMap(self, numFile = "test.map"):
        with open(numFile,'wb') as f:
            pickle.dump(self, f)
            f.close()
        
    def loadMap(self, numfile):
        with open(numfile, "rb") as f:
            dummy = pickle.load(f)
            self.n = dummy.n
            self.m = dummy.m
            self.surface = dummy.surface
            f.close()
        
    def image(self, colour = BLUE, background = WHITE):
        imagine = pygame.Surface((400,400))
        brick = pygame.Surface((20,20))
        brick.fill(BLUE)
        imagine.fill(WHITE)
        for i in range(self.n):
            for j in range(self.m):
                if (self.surface[i][j] == 1):
                    imagine.blit(brick, ( j * 20, i * 20))
                
        return imagine        
        

class Drone():
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
    def move(self, detectedMap):
        pressed_keys = pygame.key.get_pressed()
        if self.x > 0:
            if pressed_keys[K_UP] and detectedMap.surface[self.x-1][self.y]==0:
                self.x = self.x - 1
        if self.x < 19:
            if pressed_keys[K_DOWN] and detectedMap.surface[self.x+1][self.y]==0:
                self.x = self.x + 1
        
        if self.y > 0:
            if pressed_keys[K_LEFT] and detectedMap.surface[self.x][self.y-1]==0:
                self.y = self.y - 1
        if self.y < 19:        
            if pressed_keys[K_RIGHT] and detectedMap.surface[self.x][self.y+1]==0:
                 self.y = self.y + 1
                  
    def mapWithDrone(self, mapImage):
        drona = pygame.image.load("drona.png")
        mapImage.blit(drona, (self.y * 20, self.x * 20))
        
        return mapImage


def searchGreedy(mapM, droneD, initialX, initialY, finalX, finalY):
    found = False
    visited = []
    toVisit = [(initialX, initialY)]
    parent = {
        (initialX, initialY): None
    }
    walk = []

    while len(toVisit) > 0 and not found:
        if len(toVisit) == 0:
            return False
        node = toVisit[0]
        toVisit = toVisit[1:]
        visited = visited + [(node[0], node[1])]
        
        if node[0] == finalX and node[1] == finalY: 
            found = True
        walk.append(node)
        aux = []
        for i in range(len(v)):
            next_X = node[0] + v[i][0]
            next_Y = node[1] + v[i][1]
            pos = (next_X, next_Y)

            if withinBounds(next_X) and withinBounds(next_Y) and mapM.surface[next_X][next_Y] == 0 and pos not in visited and pos not in toVisit:
                aux.append(pos)

        if len(aux) > 0:
            aux.sort(key=lambda x: distanceFromDestination(x, (finalX, finalY)))
            toVisit = toVisit + [aux[0]]
            toVisit.sort(key=lambda x: distanceFromDestination(x, (finalX, finalY)))
            parent[aux[0]] = [(node[0], node[1])]

    current_pos = (finalX, finalY)
    path = [(finalX, finalY)]
    # print(parent)
    while current_pos in parent and parent[current_pos] is not None:
        path = parent[current_pos] + path
        current_pos = parent[current_pos][0]

    # TO DO 
    # implement the search function and put it in controller
    # returns a list of moves as a list of pairs [x,y]
    print("Found" if found else "Not found")
    return path if found else walk
    # TO DO 
    # implement the search function and put it in controller
    # returns a list of moves as a list of pairs [x,y] 
    
    pass

def searchAStar(mapM, droneD, initialX, initialY, finalX, finalY):
    found = False
    visited = []
    toVisit = [(initialX, initialY)]
    parent = {
        (initialX, initialY): None
    }

    while len(toVisit) > 0 and not found:
        if len(toVisit) == 0:
            return False
        node = toVisit[0]
        toVisit = toVisit[1:]
        visited = visited + [(node[0], node[1])]
        
        if node[0] == finalX and node[1] == finalY: 
            found = True
        
        aux = []
        for i in range(len(v)):
            next_X = node[0] + v[i][0]
            next_Y = node[1] + v[i][1]
            pos = (next_X, next_Y)

            if withinBounds(next_X) and withinBounds(next_Y) and mapM.surface[next_X][next_Y] == 0 and pos not in visited and pos not in toVisit:
                if pos not in parent:
                    parent[pos] = [(node[0], node[1])]
                else:
                    
                    parent[pos].append((node[0], node[1]))
                aux.append(pos)
            
        toVisit = toVisit + aux
        toVisit.sort(key=lambda x: distanceFromDestination(x, (finalX, finalY)))

    current_pos = (finalX, finalY)
    path = [(finalX, finalY)]

    while parent[current_pos] is not None:
        path = parent[current_pos] + path
        current_pos = parent[current_pos][0]

    # TO DO 
    # implement the search function and put it in controller
    # returns a list of moves as a list of pairs [x,y]
    return path

def withinBounds(x):
    return x >= 0 and x <= 19

def distanceFromDestination(source, dest):
    return abs(dest[0] - source[0]) + abs(dest[1] - source[1])

def dummysearch():
    #example of some path in test1.map from [5,7] to [7,11]
    return [[5,7],[5,8],[5,9],[5,10],[5,11],[6,11],[7,11]]
    
def displayWithPath(image, greedy, astar, gcolor, acolor):
    mark = pygame.Surface((20,20))
    mark.fill(gcolor)
    for move in greedy:
        image.blit(mark, (move[1] *20, move[0] * 20))
        
    mark.fill(acolor)
    for move in astar:
        image.blit(mark, (move[1] *20, move[0] * 20))

    return image

                  
# define a main function
def main():
    
    # we create the map
    m = Map() 
    #m.randomMap()
    #m.saveMap("test2.map")
    m.loadMap("test1.map")
    
    # initialize the pygame module
    pygame.init()
    # load and set the logo
    logo = pygame.image.load("logo32x32.png")
    pygame.display.set_icon(logo)
    pygame.display.set_caption("Path in simple environment")
        
    # we position the drone somewhere in the area
    x = randint(0, 19)
    y = randint(0, 19)
    
    #create drona
    d = Drone(x, y)
    
    # create a surface on screen that has the size of 400 x 480
    screen = pygame.display.set_mode((400,400))
    screen.fill(WHITE)
    
    # define a variable to control the main loop
    running = True
     
    # main loop
    while running:
        # event handling, gets all event from the event queue
        for event in pygame.event.get():
            # only do something if the event is of type QUIT
            
            if event.type == KEYDOWN:
                running = False
                # d.move(m) #this call will be erased        
        
        screen.blit(d.mapWithDrone(m.image()),(0,0))
        pygame.display.flip()

    timer = Timer()
    timer.start()

    greedyPath = searchGreedy(m, d, 5, 7, 0, 19)
    print(f"Greedy: {timer.elapsed():0.4f} seconds")
    
    timer.reset()
    timer.start()
    
    aStarPath = searchAStar(m, d, 5, 7, 0, 19)
    print(f"A*: {timer.elapsed():0.4f} seconds")
    
    screen.blit(displayWithPath(m.image(), greedyPath, aStarPath, RED, GREEN),(0,0))
    
    pygame.display.flip()
    time.sleep(5)
    pygame.quit()
     
# run the main function only if this module is executed as the main script
# (if you import this as a module then nothing is executed)
if __name__=="__main__":
    # call the main function
    main()


