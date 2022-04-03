# -*- coding: utf-8 -*-

import pickle
from domain import *


class repository():
    def __init__(self):
         
        self.__populations = []
        self.cmap = Map()
        self.cmap.randomMap()
        self.drone = [0, 0]
        
    def createPopulation(self, args):
        # args = [populationSize, individualSize] -- you can add more args    
        return Population(args[0], args[1])

    def create_population_from_individuals(self, populationSize, individuals):
        return Population.from_individuals(self.cmap, self.drone, populationSize, individuals)

    def add_population(self, population): self.__populations.append(population)

    def set_drone(self, x, y): self.drone = [x, y]

    def add_individual(self, population, individual):
        population.add_individual(individual, self.cmap, self.drone)

    def evaluate_population(self, population):
        population.evaluate(self.cmap, self.drone)

    def get_current_population(self): return self.__populations[-1]

    def get_average_and_std(self):
        return self.get_current_population().compute_average_fitness_and_std(self.cmap, self.drone)

    def get_best_path(self):
        return self.get_current_population().get_best_path(self.drone)
    # TO DO : add the other components for the repository: 
    #    load and save from file, etc
            