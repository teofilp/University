//
// Created by Teodor Filp on 10/04/2021.
//

#pragma once
#ifndef A2_MOVIEFILERW_H
#define A2_MOVIEFILERW_H

#include <vector>
#include <fstream>

#include "../domain/Movie.h"

using std::vector;

class MovieFileRW {
    private:
        string fileName;
    public:
        MovieFileRW(string);
        void saveMovies(vector<Movie>&);
        vector<Movie> loadMovies();
};

#endif //A2_MOVIEFILERW_H
