import pygame
from constants.colors import GREY, WHITE, BLACK

class DMapView:
    def __init__(self, dmap):
        self.dmap = dmap

    def image(self, x, y):
        imagine = pygame.Surface((420,420))
        brick = pygame.image.load("ghost.png")
        # empty = pygame.image.load("russia.png")
        # brick = pygame.Surface((20,20))
        empty = pygame.Surface((20,20))
        empty.fill(WHITE)
        # brick.fill(BLACK)
        imagine.fill(GREY)
        
        for i in range(self.dmap.get_n()):
            for j in range(self.dmap.get_m()):
                if (self.dmap.surface[i][j] == 1):
                    imagine.blit(brick, ( j * 20, i * 20))
                elif (self.dmap.surface[i][j] == 0):
                    imagine.blit(empty, ( j * 20, i * 20))
                
        drona = pygame.image.load("pacman.png")
        imagine.blit(drona, (y *20, x*20))
        return imagine