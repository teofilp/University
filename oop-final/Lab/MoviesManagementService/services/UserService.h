//
// Created by Teodor Filp on 24/03/2021.
//

#ifndef A2_USERSERVICE_H
#define A2_USERSERVICE_H

#include "../repositories/MoviesRepository.h"
#include "../data-display/DataDisplayHandler.h"
#include "../console/views/user/ViewWatchlistTab.h"

class UserService {
    private:
        MoviesRepository *movies;
        DataDisplayHandler *previewHandler;
        MoviesRepository *watchlist;

    public:
        // constructor
        UserService(MoviesRepository&, MoviesRepository&);

        UserService(MoviesRepository&, MoviesRepository&, DataDisplayHandler&);

        // returns a copy of available movies filtered by genre
        vector<Movie> getByGenre(string);

        // adds a new movie from the movies repository to the watchlist
        void addToWatchlist(Movie);

        // returns a list representing the current user's watchlist
        vector<Movie>& getWatchlist();

        // removes a movie identified by its id from the watchlist
        void removeFromWatchlist(int);

        void increaseLikes(int);

        void previewWatchlist();
        void previewWatchlist(DataDisplayHandler*);
};
#endif //A2_USERSERVICE_H
