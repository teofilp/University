//
// Created by Teodor Filp on 10/04/2021.
//
#include "MovieValidator.h"

MovieValidator::MovieValidator(Movie &movie): movie(movie) {}

bool MovieValidator::isValid() {
    return isTitleValid() &&
            isGenreValid() &&
            isTrailerValid() &&
            isReleaseYearValid() &&
            isLikesValid();
}

bool MovieValidator::isTitleValid() {
    return !movie.getTitle().empty();
}

bool MovieValidator::isGenreValid() {
    return !movie.getGenre().empty();
}

bool MovieValidator::isTrailerValid() {
    return !movie.getGenre().empty();
}

bool MovieValidator::isReleaseYearValid() {
    return movie.getReleaseYear() > 0;
}

bool MovieValidator::isLikesValid() {
    return movie.getLikes() >= 0;
}