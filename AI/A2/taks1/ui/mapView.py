import pygame
from constants.colors import WHITE, BLUE

class MapView:
    def __init__(self, map):
        self.map = map

    def image(self, colour = BLUE, background = WHITE):
        imagine = pygame.Surface((400,400))
        brick = pygame.Surface((20,20))
        brick.fill(colour)
        imagine.fill(background)
        for i in range(self.map.n):
            for j in range(self.map.m):
                if (self.map.surface[i][j] == 1):
                    imagine.blit(brick, ( j * 20, i * 20))
                
        return imagine    