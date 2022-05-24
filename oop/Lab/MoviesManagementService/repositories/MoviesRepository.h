//
// Created by Teodor Filp on 20/03/2021.
//

#ifndef A2_MOVIESREPOSITORY_H
#define A2_MOVIESREPOSITORY_H

#include <vector>
#include "../domain/Movie.h"
#include "../file-handler/MovieFileRW.h"

using std::vector;

class MoviesRepository {
    private:
        vector<Movie> movies;
        MovieFileRW *fileHandler;

        void save();
        void load();
        int getNextId();
    public:
        MoviesRepository();
        // constructor
        MoviesRepository(MovieFileRW&);

        // adds a new movie to the db
        void add(Movie);

        // updates an existing movie
        void update(Movie);

        // removes a movie from the db
        void remove(int);

        // gets all the movies from the db
        vector<Movie>& getAll();

        // finds a movie by its unique identifier
        Movie& find(int);

        vector<Movie>::iterator findIterator(int);

        void overwriteRepo(vector<Movie> movies);
};

#endif //A2_MOVIESREPOSITORY_H
