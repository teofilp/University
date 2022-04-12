from repository import *
from random import seed
import numpy as np

class controller():
    def __init__(self, repository):
        self._repository = repository
        self._seeds_number = 10
        self._steps_number = 20
        self._population_size = 25
        self._iterations_number = 10
        self._mutation_probability = 0.5
        self._crossover_probability = 0.8
        self._statistics = []
        self._iteration = 0
        # args - list of parameters needed in order to create the controller
        pass
    

    def iteration(self, args=0):
        # args - list of parameters needed to run one iteration
        # a iteration:
        # selection of the parrents
        # create offsprings by crossover of the parents
        # apply some mutations
        # selection of the survivors
        population = self._repository.get_current_population()
        self._repository.evaluate_population(population)
        selection = population.selection(self._population_size)

        new_individuals = []

        for i in range(len(selection)):
            parent1 = selection[i]
            for j in range(i+1, len(selection)):
                parent2 = selection[j]

                child1, child2 = parent1.crossover(parent2, self._crossover_probability)

                parent1.mutate(self._mutation_probability)
                parent2.mutate(self._mutation_probability)

                new_individuals.append(child1)
                new_individuals.append(child2)

                if parent1 not in new_individuals:
                    new_individuals.append(parent1)
                if parent2 not in new_individuals:
                    new_individuals.append(parent2)

        new_population = self._repository.create_population_from_individuals(self._population_size, new_individuals)

        self._repository.add_population(new_population)

        
    def run(self, args=0):
        # args - list of parameters needed in order to run the algorithm
        
        # until stop condition
        #    perform an iteration
        #    save the information need it for the satistics
        
        # return the results and the info for statistics
        scoped_statistics = []

        for i in range(self._iterations_number):
            self._iteration += 1
            self.iteration()

            data = self._repository.get_average_and_std()

            scoped_statistics.append(data[0])
        
        return scoped_statistics
    
    def solver(self, args=0):
        # self._statistics = []
        averages = []
        # args - list of parameters needed in order to run the solver
        for s in range(self._seeds_number):
            print ("Seed: " + str(s))
            seed(s)
            population = self._repository.createPopulation([self._population_size, self._steps_number])
            self._repository.add_population(population)
            averages.append(self.run()[-1])
        # create the population,
        # run the algorithm
        # return the results and the statistics
        return np.std(averages)

    def solve_once(self):
        population = self._repository.createPopulation([self._population_size, self._steps_number])
        self._repository.add_population(population)
        averages = self.run()

        return self._repository.get_best_path(), averages


    def set_steps(self, value): self._steps_number = value

    def set_seeds(self, value): self._seeds_number = value

    def set_population_size(self, value): self._population_size = value

    def set_iterations(self, value): self._iterations_number = value

    def set_mutation(self, value): self._mutation_probability = value

    def set_crossover(self, value): self._crossover_probability = value