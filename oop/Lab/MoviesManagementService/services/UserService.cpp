//
// Created by Teodor Filp on 24/03/2021.
//
#include "UserService.h"

#include <algorithm>

UserService::UserService(MoviesRepository &repo, MoviesRepository &watchList, DataDisplayHandler &handler) {
    this->movies = &repo;
    this->watchlist = &watchList;
    this->previewHandler = &handler;
}

UserService::UserService(MoviesRepository &repo, MoviesRepository &watchlist) {
    this->movies = &repo;
    this->watchlist = &watchlist;
}

vector<Movie> UserService::getByGenre(string genre) {
    vector<Movie> movies = this->movies->getAll();
    vector<Movie> filtered(movies.size());

    if (genre.empty()) {
        return movies;
    }

    auto it = std::copy_if(movies.begin(), movies.end(), filtered.begin(), [&genre](const Movie& movie) {return movie.getGenre() == genre; });

    filtered.resize(std::distance(filtered.begin(), it));
    return filtered;
}

void UserService::addToWatchlist(Movie m) {
    this->watchlist->add(m);
}

vector<Movie>& UserService::getWatchlist() {
    return this->watchlist->getAll();
}

void UserService::removeFromWatchlist(int id) {
    this->watchlist->remove(id);
}

void UserService::previewWatchlist() {
    if (previewHandler == nullptr) {
        throw std::runtime_error("wtf bro");
    }
    previewHandler->generatePreview(this->getWatchlist());
    previewHandler->preview();
}

void UserService::increaseLikes(int id) {
    Movie m = movies->find(id);
    m.setLikes(m.getLikes() + 1);
    movies->update(m);
}

void UserService::previewWatchlist(DataDisplayHandler *handler) {
    handler->generatePreview(this->getWatchlist());
    handler->preview();
}
