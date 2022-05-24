//
// Created by Teodor Filp on 01/05/2021.
//

#ifndef TESTQT_SEARCHTAB_H
#define TESTQT_SEARCHTAB_H

#include <QWidget>
#include <QGridLayout>
#include <QLineEdit>
#include <QPushButton>
#include <vector>
#include "../../../services/UserService.h"

class SearchTab: public QWidget {
    private:
        UserService *userService;
        QGridLayout *mainLayout;
        QLineEdit *searchInput;
        QPushButton *searchButton;
        QGridLayout *bottomLayout;
        QWidget *bottomWidget;
        QLineEdit *title;
        QLineEdit *genre;
        QLineEdit *releaseYear;
        QLineEdit *likes;
        QPushButton *nextMovieButton;
        QPushButton *addToWatchlistButton;
        vector<Movie> filteredMovies;
        int currentIndex;

        void setupLayout();
        void handleSearch();
        void disableFields();
        void handleNext();
        void handleAddToWatchlist();
        void warn(string);
        void updateView();
        void previewTrailer(string);

    public:
        SearchTab(UserService*);
};


#endif //TESTQT_SEARCHTAB_H
