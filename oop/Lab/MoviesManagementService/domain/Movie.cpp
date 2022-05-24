//
// Created by Teodor Filp on 20/03/2021.
//
#include <string>

#include "./Movie.h"
#include "../builders/FormatMovieBuilder.h"
#include <iostream>

using std::ostream;
using std::string;

ostream& operator<<(ostream& os, const Movie& movie) {
    return os << movie.getFormattedMovie() << "\n";
}

Movie::Movie() {}

Movie::Movie(string title, string genre, string trailerLink, int releaseYear, int likes) {
    setTitle(title);
    setGenre(genre);
    setTrailerLink(trailerLink);
    setLikes(likes);
    setReleaseYear(releaseYear);
}

void Movie::setTitle(string title) {
    this->title = title;
}

void Movie::setGenre(string genre) {
    this->genre = genre;
}

void Movie::setTrailerLink(string link) {
    this->trailerLink = link;
}

void Movie::setReleaseYear(int year) {
    this->releaseYear = year;
}

void Movie::setLikes(int likes) {
    this->likes = likes;
};

string Movie::getTitle() const {
    return this->title;
}

string Movie::getGenre() const {
    return this->genre;
}

string Movie::getTrailerLink() const {
    return this->trailerLink;
}

int Movie::getReleaseYear() const {
    return this->releaseYear;
}

int Movie::getLikes() const {
    return this->likes;
}

string Movie::getFormattedMovie() const {
    string movie =
            FormatMovieBuilder::init()
            .withId(this->id)
            .withTitle(this->getTitle())
            .withGenre(this->getGenre())
            .withTrailerLink(this->getTrailerLink())
            .withReleaseYear(this->getReleaseYear())
            .withLikes(this->getLikes())
            .build();
    return movie;
}

