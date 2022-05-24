//
// Created by Teodor Filp on 22/03/2021.
//
#include <assert.h>
#include <iostream>

#include "domain_tests.h"
#include "../domain/Entity.h"
#include "../domain//Movie.h"

void testEntityOperators();

void runMovieTests();

void testMovieConstructorsAndGetters();

void testMovieFormattedString();

void testEntityConstructor() {
    Entity e1(10);
    Entity e;

    assert(e1.id == 10);
    assert(sizeof (e.id) == sizeof(int));
}

void runEntityTests() {
    testEntityConstructor();
    testEntityOperators();
}

void testEntityOperators() {
    Entity e1(10);
    Entity e2(1);
    Entity e3(10);

    assert(e1 == e3);
    assert(e1 != e2);
}


void runDomainTests() {
    runEntityTests();
    runMovieTests();
}

void runMovieTests() {
    testMovieConstructorsAndGetters();
    testMovieFormattedString();
}

void testMovieFormattedString() {
    Movie m("title", "genre", "link", 2020, 0);
    m.id = 1;
    assert(m.getFormattedMovie() == "Id: 1 | Title: title | Genre: genre | Trailer: link | Year: 2020 | Likes: 0");
}

void testMovieConstructorsAndGetters() {
    Movie m;
    assert(sizeof(m.id) == sizeof(int));

    Movie m2("title", "genre", "link", 2020, 0);

    assert(m2.getTitle() == "title");
    assert(m2.getGenre() == "genre");
    assert(m2.getTrailerLink() == "link");
    assert(m2.getLikes() == 0);
    assert(m2.getReleaseYear() == 2020);
}
