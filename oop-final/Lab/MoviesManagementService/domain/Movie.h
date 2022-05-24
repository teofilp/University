//
// Created by Teodor Filp on 20/03/2021.
//

#ifndef A2_MOVIE_H
#define A2_MOVIE_H

#include <string>
#include "./Entity.h"

using std::string;

class Movie: public Entity {
    private:
        std::string title, genre, trailerLink;
        int releaseYear, likes;
    public:
        Movie();
        Movie(string, string, string, int, int);
        void setTitle(string);
        void setGenre(string);
        void setTrailerLink(string);
        void setReleaseYear(int);
        void setLikes(int);
        string getTitle() const;
        string getGenre() const;
        string getTrailerLink() const;
        int getReleaseYear() const;
        int getLikes() const;

        // returns a strings representation of the movie
        std::string getFormattedMovie() const;
};


std::ostream& operator<<(std::ostream& os, const Movie& movie);

#endif //A2_MOVIE_H
