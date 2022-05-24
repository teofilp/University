//
// Created by Teodor Filp on 20/03/2021.
//

#include "ManagerService.h"
#include "../validators/MovieValidator.h"
#include "../exceptions/validators/MovieValidatorException.h"

ManagerService::ManagerService(MoviesRepository &repo) {
    this->repository = &repo;
}

void ManagerService::add(Movie movie) {
    MovieValidator validator(movie);
    if (!validator.isValid()) {
        throw MovieValidatorException("Invalid movie format");
    }
    this->repository->add(movie);
}

void ManagerService::update(Movie movie) {
    MovieValidator validator(movie);
    if (!validator.isValid()) {
        throw MovieValidatorException("Invalid movie format");
    }
    this->repository->update(movie);
}

void ManagerService::remove(int id) {
    this->repository->remove(id);
}

vector<Movie>& ManagerService::getAll() {
    return this->repository->getAll();
}

void ManagerService::increaseLikes(int id) {
    Movie old = this->repository->find(id);
    old.setLikes(old.getLikes() + 1);
    this->repository->update(old);
}

Movie& ManagerService::getById(int id) {
    return this->repository->find(id);
}