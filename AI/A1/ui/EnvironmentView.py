from constants.colors import BLUE, WHITE
import pygame

class EnvironmentView:
    def __init__(self, env):
        self.env = env;

    def image(self, colour = BLUE, background = WHITE):
        imagine = pygame.Surface((420,420))
        brick = pygame.Surface((20,20))
        brick.fill(BLUE)
        imagine.fill(WHITE)
        for i in range(self.env.get_n()):
            for j in range(self.env.get_m()):
                if (self.env.get_surface()[i][j] == 1):
                    imagine.blit(brick, ( j * 20, i * 20))
                
        return imagine       