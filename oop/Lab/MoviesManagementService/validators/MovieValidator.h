//
// Created by Teodor Filp on 10/04/2021.
//

#pragma once
#ifndef A2_MOVIEVALIDATOR_H
#define A2_MOVIEVALIDATOR_H
#include "../domain/Movie.h"

class MovieValidator {
    private:
        Movie& movie;
        bool isTitleValid();
        bool isGenreValid();
        bool isTrailerValid();
        bool isReleaseYearValid();
        bool isLikesValid();
    public:
        MovieValidator(Movie&);
        bool isValid();
};

#endif //A2_MOVIEVALIDATOR_H
