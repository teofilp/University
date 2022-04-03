# -*- coding: utf-8 -*-

from audioop import reverse
from random import *
from utils import *
import numpy as np
from copy import deepcopy
import pickle

# the glass gene can be replaced with int or float, or other types
# depending on your problem's representation

def gene():
    return v[randint(0, len(v) - 1)]

def can_continue(position, map):
    [x, y] = position
    return x >= 0 and x < map.n and y >= 0 and y < map.m and map.surface[x][y] != 1

class Individual:
    def __init__(self, size = 0):
        self.__size = size
        self.__x = [gene() for i in range(self.__size)]
        self.__f = None
        
    def fitness(self, map, drone):
        path = self.build_path(drone)
        visited = []

        self.__f = 1

        for position in path:
            [x, y] = position
            visited.append(position)

            if x < 0 or x >= map.n or y < 0 or y >= map.m:
                # print ('went off the board')
                break

            if map.surface[x][y] == 1:
                # print ('hit the wall')
                break

            
            for direction in v:
                current_position = [x, y]

                while can_continue(current_position, map):
                    [current_x, current_y] = current_position
                    current_position = [current_x + direction[0], current_y + direction[1]]

                    if current_position not in visited and can_continue(current_position, map):
                        visited.append(current_position)
                        self.__f += 1                     
        
        return self.__f

    def build_path(self, drone):
        path = [[drone[0], drone[1]]]

        for offset in self.__x:
            path.append([path[-1][0] + offset[0], path[-1][1] + offset[1]])

        return path
    
    def mutate(self, mutateProbability = 0.04):
        if random() < mutateProbability:
            gene_index = randint(0, len(self.__x) - 1)
            self.__x[gene_index] = v[randint(0, len(v) - 1)]        
    
    def crossover(self, otherParent, crossoverProbability = 0.8):
        offspring1, offspring2 = Individual(self.__size), Individual(self.__size) 

        if random() < crossoverProbability:
            index = randint(0, self.__size - 1)
            offspring1.__x = otherParent.__x[:index] + self.__x[index:]
            offspring2.__x = self.__x[:index] + otherParent.__x[index:]
        
        return offspring1, offspring2
    
    def get_x(self): return self.__x

    def set_x(self, index, value): self.__x[index] = value

    def get_f(self): return self.__f

    def set_f(self, value): self.__f = value
    
class Population():
    def __init__(self, populationSize = 0, individualSize = 0):
        self.__populationSize = populationSize
        self.__v = [Individual(individualSize) for x in range(populationSize)]
        
    @classmethod
    def from_individuals(cls, map, drone, populationSize, individuals):
        population = Population()
        population.set_individuals(individuals)
        
        population.evaluate(map, drone)

        selected = population.selection(populationSize)

        population.set_individuals(selected)

        return population
    
    def evaluate(self, map, drone):
        # evaluates the population
        for x in self.__v:
            x.fitness(map, drone)
            
    def selection(self, k = 0):
        copy = deepcopy(self.__v)
        
        copy.sort(key=lambda x: x.get_f(), reverse=True)

        return copy[:k]

    def compute_average_fitness_and_std(self, map, drone):
        fitness = [x.fitness(map, drone) for x in self.__v]
        return [np.average(fitness), np.std(fitness)]
    
    def add_individual(self, individual, map, drone):
        individual.fitness(map, drone)
        self.__v.append(individual)

    def set_individuals(self, individuals):
        self.__v = individuals

    def get_best_path(self, drone):
        individuals = deepcopy(self.__v)
        individuals.sort(key=lambda x: x.get_f(), reverse=True)
        return individuals[0].build_path(drone)

    
class Map():
    def __init__(self, n = 20, m = 20):
        self.n = n
        self.m = m
        self.surface = np.zeros((self.n, self.m))
    
    def randomMap(self, fill = 0.1):
        for i in range(self.n):
            for j in range(self.m):
                if random() <= fill :
                    self.surface[i][j] = 1
                
    def load_map(self, num_file):
        with open(num_file, "rb") as file:
            dummy = pickle.load(file)
            self.n = dummy.n
            self.m = dummy.m
            self.surface = dummy.surface
            file.close()

    def save_map(self, file="test.map"):
        with open(file, "wb") as f:
            pickle.dump(self, f)
            f.close()

    def __str__(self):
        string=""
        for i in range(self.n):
            for j in range(self.m):
                string = string + str(int(self.surface[i][j]))
            string = string + "\n"
        return string

