import pygame

class DroneView:
    def __init__(self, drone):
        self.drone = drone

    def mapWithDrone(self, mapImage):
        drona = pygame.image.load("drona.png")
        mapImage.blit(drona, (self.drone.y * 20, self.drone.x * 20))
        
        return mapImage