//
// Created by Teodor Filp on 20/03/2021.
//

#ifndef A2_MANAGERSERVICE_H
#define A2_MANAGERSERVICE_H

#include "../repositories/MoviesRepository.h"
#include "../file-handler/MovieFileRW.h"
#include "../data-display/DataDisplayHandler.h"

class ManagerService {
    private:
        MoviesRepository *repository;
    public:
        // constructor
        ManagerService(MoviesRepository&);

        // adds a new movie
        void add(Movie);

        // updates movie
        void update(Movie);

        // removes a movie identified by its id
        void remove(int);

        // method returns all the movies in the db
        vector<Movie>& getAll();

        // increased the number of a likes for a movie
        void increaseLikes(int);

        Movie& getById(int);
};


#endif //A2_MANAGERSERVICE_H
