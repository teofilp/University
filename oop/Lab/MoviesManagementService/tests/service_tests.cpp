//
// Created by Teodor Filp on 23/03/2021.
//
#include "../services/ManagerService.h"
#include "../repositories/MoviesRepository.h"
#include "../services/UserService.h"

void testServiceAdd();
void testServiceUpdate();
void testServiceRemove();
void testServiceIncreaseLikes();
void testServiceGetByGenre();
void testServiceAddToWatchlist();
void testServiceRemoveFromWatchlist();

void runServiceTests() {
    testServiceAdd();
    testServiceUpdate();
    testServiceRemove();
    testServiceIncreaseLikes();
    testServiceGetByGenre();
    testServiceAddToWatchlist();
    testServiceRemoveFromWatchlist();
}

void testServiceUpdate() {
    MoviesRepository repo;
    ManagerService service(repo);
    Movie m("test", "test", "test", 2020, 0);

    try {
        service.update(m);
        assert(false);
    } catch (std::runtime_error &ex) {
        assert(true);
    }

    service.add(m);
    m.id = 1;

    m.setLikes(100);

    service.update(m);
    assert (service.getAll()[0].getLikes() == m.getLikes());
}

void testServiceIncreaseLikes() {
    MoviesRepository repo;
    ManagerService service(repo);

    service.add(Movie("test", "test", "test", 2020, 0));
    service.increaseLikes(1);

    Movie m = service.getAll()[0];
    assert(m.getLikes() == 1);
}

void testServiceRemove() {
    MoviesRepository repo;
    ManagerService service(repo);

    try {
        service.remove(1);
        assert(false);
    } catch (std::runtime_error &ex) {
        assert(true);
    }

    service.add(Movie("Test", "Test", "test", 2020, 0));
    service.remove(1);

    assert(service.getAll().size() == 0);
}

void testServiceAdd() {
    MoviesRepository repo;
    ManagerService service(repo);

    service.add(Movie("test", "test", "Tset", 2020, 0));
    assert(service.getAll().size() == 1);

    service.add(Movie("test", "test", "Tset", 2020, 0));
    assert(service.getAll().size() == 2);

    Movie m;
    m.id = 1;

    try {
        service.add(m);
        assert(false);
    } catch (std::runtime_error &ex) {
        assert(true);
    }
}

void testServiceGetByGenre() {
    MoviesRepository repo, watchlist;
    ManagerService service(repo);
    UserService userService(repo, watchlist);

    service.add(Movie("test", "test", "test", 2020, 0));

    assert(userService.getByGenre("test").size() == 1);
    assert(userService.getByGenre("").size() == 1);
    assert(userService.getByGenre("r").size() == 0);
}

void testServiceAddToWatchlist() {
    MoviesRepository repo, watchlist;
    ManagerService service(repo);
    UserService userService(repo, watchlist);

    service.add(Movie("test", "test", "test", 2020, 0));
    Movie added = service.getAll()[0];

    userService.addToWatchlist(added);
    assert(userService.getWatchlist().size() == 1);
}

void testServiceRemoveFromWatchlist() {
    MoviesRepository repo, watchlist;
    ManagerService service(repo);
    UserService userService(repo, watchlist);

    service.add(Movie("test", "test", "test", 2020, 0));
    Movie added = service.getAll()[0];

    userService.addToWatchlist(added);

    userService.removeFromWatchlist(1);

    assert(userService.getWatchlist().size() == 0);
}
