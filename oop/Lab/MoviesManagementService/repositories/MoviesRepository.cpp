//
// Created by Teodor Filp on 20/03/2021.
//

#include "MoviesRepository.h"
#include "../exceptions/repository/RepositoryException.h"

#include <algorithm>

MoviesRepository::MoviesRepository() {
    this->fileHandler = nullptr;
}

MoviesRepository::MoviesRepository(MovieFileRW &handler) {
    this->fileHandler = &handler;
    this->load();
}

void MoviesRepository::save() {
    if (this->fileHandler != nullptr) {
        this->fileHandler->saveMovies(this->movies);
    }
}

void MoviesRepository::load() {
    if (this->fileHandler != nullptr) {
        this->movies = this->fileHandler->loadMovies();
    }
}

void MoviesRepository::add(Movie movie) {
    auto it = this->findIterator(movie.id);
    if (it != this->movies.end()) {
        throw RepositoryException("Item already present");
    }
    if (movie.id < 0){
        movie.id = getNextId();
    }
    this->movies.push_back(movie);
    this->save();
}

int MoviesRepository::getNextId() {
    if (this->movies.size() == 0) {
        return 1;
    } else {
        int maxi = 1;
        for (auto &movie: this->movies) {
            if (movie.id >= maxi) {
                maxi = movie.id;
            }
        }
        return maxi + 1;
    }
}

void MoviesRepository::update(Movie updated) {
    Movie &movie = this->find(updated.id);
    movie = updated;
    this->save();
}

void MoviesRepository::remove(int id) {
    auto it = this->findIterator(id);
    if (it == this->movies.end()) {
        throw RepositoryException("Item was not found");
    }
    this->movies.erase(this->findIterator(id));
    this->save();
}

vector<Movie>& MoviesRepository::getAll() {
    return this->movies;
}

Movie& MoviesRepository::find(int id) {
    auto iterator = find_if(this->movies.begin(), this->movies.end(), [&id] (const Movie &m) { return m.id == id; });
    if (iterator == this->movies.end()) {
        throw RepositoryException("Item not found");
    }
    return (*iterator);
}

vector<Movie>::iterator MoviesRepository::findIterator(int id) {
    return find_if(this->movies.begin(), this->movies.end(), [&id] (const Movie &m) { return m.id == id; });
}

void MoviesRepository::overwriteRepo(vector<Movie> movies) {
    this->movies = movies;
    this->save();
}
