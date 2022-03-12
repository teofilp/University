import pygame
from random import randint
from pygame.locals import *
from models.DMap import DMap
from models.Drone import Drone
from ui.DMapView import DMapView
from models.Environment import Environment
from ui.EnvironmentView import EnvironmentView
from constants.colors import WHITE
                     
                  
# define a main function
def main():
    #we create the environment
    e = Environment()
    # e.loadEnvironment("test2.map")
    e.randomMap(.35)
    ev = EnvironmentView(e)
    
    # we create the map
    m = DMap() 
    mv = DMapView(m)
    
    
    # initialize the pygame module
    pygame.init()
    # load and set the logo
    logo = pygame.image.load("logo32x32.png")
    pygame.display.set_icon(logo)
    pygame.display.set_caption("drone exploration")
    
    #cream drona
    d = Drone(randint(0, 19), randint(0, 19))
    
    # create a surface on screen that has the size of 800 x 480
    screen = pygame.display.set_mode((800,400))
    screen.fill(WHITE)
    screen.blit(ev.image(), (0,0))
    
    # define a variable to control the main loop
    running = True
     
    # main loop
    while running:
        pygame.event.pump()

        next_pos = d.moveDSF(m, e)

        if next_pos == 0:
            running = False

        m.markDetectedWalls(e, d.x, d.y)
        screen.blit(mv.image(d.x,d.y),(400,0))
        pygame.display.flip()
        pygame.time.delay(1000)
    
    print("Game over")
    pygame.time.delay(5000)
    pygame.quit()
     
     
# run the main function only if this module is executed as the main script
# (if you import this as a module then nothing is executed)
if __name__=="__main__":
    # call the main function
    main()