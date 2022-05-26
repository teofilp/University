//
// Created by Teodor Filp on 22/03/2021.
//
#include "./repository_tests.h"
#include "../domain/Movie.h"
#include "../repositories/MoviesRepository.h"

void testUpdate();

void testRemove();

void testFind();

void testAdd() {
    MoviesRepository repo;
    Movie m1("ceva", "altceva", "test", 1, 0);
    Movie m2("ceva", "altceva", "test", 2, 0);
    m1.id = 1;
    repo.add(m1);

    assert(repo.getAll().size() == 1);

    repo.add(m2);

    assert(repo.getAll().size() == 2);

    try {
        repo.add(m1);
        assert(false);
    } catch (std::exception &ex) {
        assert(true);
    }
}

void runRepositoryTests() {
    testAdd();
    testUpdate();
    testRemove();
    testFind();
}

void testRemove() {
    MoviesRepository repo;
    try {
        repo.remove(1);
        assert(false);
    } catch (std::runtime_error &ex) {
        assert(true);
    }

    repo.add(Movie("ceva", "altceva", "blabla", 2020, 100));

    assert(repo.getAll().size() == 1);

    repo.remove(1);

    assert(repo.getAll().size() == 0);
}

void testFind() {
    MoviesRepository repo;
    try {
        repo.find(1);
        assert(false);
    } catch (std::runtime_error &ex) {
        assert(true);
    }

    repo.add(Movie("ceva", "Atlceva", "blabla", 2020, 0));
    Movie m = repo.find(1);
    assert(m.getTitle() == "ceva");
}

void testUpdate() {
    MoviesRepository repo;
    Movie m("ceva", "altceva", "test", 1, 0);
    m.id = 1;
    repo.add(m);

    Movie invalidUpdate("ceva", "altceva", "test", 2020, 0);
    invalidUpdate.id = 10;
    try {
        repo.update(invalidUpdate);
        assert(false);
    } catch (std::runtime_error &ex) {
        assert(true);
    }

    m.setLikes(100);
    repo.update(m);
}
