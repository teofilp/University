# -*- coding: utf-8 -*-


# imports
from gui import *
from controller import *
from repository import *
from domain import *
import matplotlib
import matplotlib.pyplot as plt

class Ui:
    def __init__(self, controller, repository): 
        self._controller = controller
        self._repository = repository
        self._path = []
        self._stats = []

    def print_menu(self):
        print("1. create random map")
        print("2. load a map")
        print("3. save a map")
        print("4. parameters setup")
        print("5. run the solver")
        print("6. visualise the statistics")
        print("7. view the drone moving on path")
    
    def load_map(self):
        print("Enter map filename: ")
        file_name = input()

        self._repository.cmap.load_map(file_name)

    def run_solver(self):
        self._path, self._stats = self._controller.solver()
        self.view_drone_moving()

    def run(self):
        while True:
            self.print_menu()
            option = int(input())

            if option == 1:
                self.random_map()
            elif option == 2:
                self.load_map()
            elif option == 3:
                self.save_map()
            elif option == 4:
                self.setup_parameters()
            elif option == 5:
                self.run_solver()
            elif option == 6:
                self.view_stats()
            elif option == 7:
                self.view_drone_moving()

    def setup_parameters(self):
        seeds = int(input("Enter seeds number: "))
        size = int(input("Enter population size: "))
        steps = int(input("Enter drone steps: "))
        iterations = int(input("Enter interations number: "))
        mutation = float(input("Enter mutation probability: "))
        crossover = float(input("Enter crossover probability: "))

        self._controller.set_seeds(seeds)
        self._controller.set_steps(steps)
        self._controller.set_population_size(size)
        self._controller.set_iterations(iterations)
        self._controller.set_mutation(mutation)
        self._controller.set_crossover(crossover)
            
    def random_map(self):
        self._repository.cmap.randomMap()

    def save_map(self):
        print("Enter filename: ")
        file_name = input()
        self._repository.cmap.save_map(file_name)

    def view_stats(self):
        indices = [x+1 for x in range(0, len(self._stats))]
        averages = list(map(lambda x: x[0], self._stats))
        stds = list(map(lambda x: x[1], self._stats))

        plt.plot(indices, averages, "-b", label="Average")
        plt.plot(indices, stds, "-r", label="Standard deviation")
        plt.legend(loc="upper left")

        plt.show()
        

    def view_drone_moving(self):
        movingDrone(self._repository.cmap, self._path, .5)

# create a menu
#   1. map options:
#         a. create random map
#         b. load a map
#         c. save a map
#         d visualise map
#   2. EA options:
#         a. parameters setup
#         b. run the solver
#         c. visualise the statistics
#         d. view the drone moving on a path
#              function gui.movingDrone(currentMap, path, speed, markseen)
#              ATENTION! the function doesn't check if the path passes trough walls


repo = repository()
contrl = controller(repo)
Ui(contrl, repo).run()