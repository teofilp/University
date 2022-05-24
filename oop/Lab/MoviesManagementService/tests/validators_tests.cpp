//
// Created by Teodor Filp on 13/04/2021.
//
#include "validators_tests.h"
#include "../domain/Movie.h"
#include "../validators/MovieValidator.h"

void runValidatorsTests() {
    Movie m("", "", "", -100, -100);
    MovieValidator validator(m);

    assert(validator.isValid() == false);

    m.setTitle("updated");
    assert(validator.isValid() == false);

    m.setGenre("updated");
    assert(validator.isValid() == false);

    m.setTrailerLink("updated");
    assert(validator.isValid() == false);

    m.setLikes(100);
    assert(validator.isValid() == false);

    m.setReleaseYear(2020);
    assert(validator.isValid() == true);
}
