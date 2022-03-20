from models.drone import Drone
from models.map import Map
from ui.droneView import DroneView
from ui.mapView import MapView
import pygame,time
from pygame.locals import *
from random import randint
from timer import Timer
from constants.colors import WHITE, RED, GREEN

#define indexes variations 
v = [[-1, 0], [1, 0], [0, 1], [0, -1]]

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
    currentCost = {
        (initialX, initialY): 0
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

                currentCost[(next_X, next_Y)] = currentCost[(node[0], node[1])] + 1

                aux.append(pos)
        toVisit = toVisit + aux
        toVisit.sort(key=lambda x: currentCost[(x[0], x[1])] + distanceFromDestination(x, (finalX, finalY)))

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
    mv = MapView(m)
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
    dv = DroneView(d)
    
    screen = pygame.display.set_mode((400,400))
    screen.fill(WHITE)
    
    running = True
     
    while running:
        for event in pygame.event.get():
            
            if event.type == KEYDOWN:
                running = False   
        
        screen.blit(dv.mapWithDrone(mv.image()),(0,0))
        pygame.display.flip()

    timer = Timer()
    timer.start()

    greedyPath = searchGreedy(m, d, 5, 7, 19, 19)
    print(f"Greedy: {timer.elapsed():0.4f} seconds")
    
    timer.reset()
    timer.start()
    
    aStarPath = searchAStar(m, d, 5, 7, 19, 19)
    print(f"A*: {timer.elapsed():0.4f} seconds")
    
    screen.blit(displayWithPath(mv.image(), greedyPath, aStarPath, RED, GREEN),(0,0))
    
    pygame.display.flip()
    time.sleep(25)
    pygame.quit()
     
if __name__=="__main__":
    main()