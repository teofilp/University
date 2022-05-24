//
// Created by Teodor Filp on 01/05/2021.
//

#include "SearchTab.h"
#include "../../../domain/Movie.h"
#include <string>
#include <vector>
#include <iostream>
#include <QMessageBox>

using std::string, std::vector;

SearchTab::SearchTab(UserService *service) {
    userService = service;
    mainLayout = new QGridLayout();
    searchInput = new QLineEdit();
    searchButton = new QPushButton("Search");
    bottomLayout = new QGridLayout();
    bottomWidget = new QWidget();
    title = new QLineEdit();
    genre = new QLineEdit();
    likes = new QLineEdit();
    releaseYear = new QLineEdit();
    nextMovieButton = new QPushButton("Next");
    addToWatchlistButton = new QPushButton("Add to watchlist");
    setupLayout();
    disableFields();
}

void SearchTab::setupLayout() {
    mainLayout->addWidget(searchInput, 1, 0, 1, 2);
    mainLayout->addWidget(searchButton, 1, 3);
    mainLayout->addWidget(bottomWidget, 2, 1);

    bottomLayout->addWidget(title, 1, 0);
    bottomLayout->addWidget(genre, 1, 1);
    bottomLayout->addWidget(releaseYear, 1, 3);
    bottomLayout->addWidget(likes, 1, 4);
    bottomLayout->addWidget(addToWatchlistButton, 2, 3);
    bottomLayout->addWidget(nextMovieButton, 2, 4);

    bottomWidget->hide();
    bottomWidget->setLayout(bottomLayout);

    this->setLayout(mainLayout);

    QObject::connect(searchButton, &QPushButton::clicked, this, &SearchTab::handleSearch);
    QObject::connect(nextMovieButton, &QPushButton::clicked, this, &SearchTab::handleNext);
    QObject::connect(addToWatchlistButton, &QPushButton::clicked, this, &SearchTab::handleAddToWatchlist);
}

void SearchTab::handleSearch() {
    string key = searchInput->text().toStdString();
    filteredMovies = userService->getByGenre(key);
    currentIndex = 0;
    bool hasResults = filteredMovies.size() > 0;
    if (hasResults) {
        bottomWidget->show();
        updateView();
    } else {
        bottomWidget->hide();
        searchInput->clear();
        warn("No movies were found!");
    }
}

void SearchTab::disableFields() {
    title->setEnabled(false);
    genre->setEnabled(false);
    releaseYear->setEnabled(false);
    likes->setEnabled(false);
}

void SearchTab::handleNext() {
    currentIndex += 1;
    currentIndex %= filteredMovies.size();
    updateView();
}

void SearchTab::handleAddToWatchlist() {
    Movie movie = filteredMovies[currentIndex];
    try {
        userService->addToWatchlist(movie);
        warn("Item added successfully!");
    } catch (std::exception &ex) {
        warn(ex.what());
    }
}

void SearchTab::warn(string message) {
    QMessageBox msgBox;
    msgBox.setText(message.c_str());
    msgBox.exec();
}

void SearchTab::updateView() {
    Movie movie = filteredMovies[currentIndex];

    title->setText(movie.getTitle().c_str());
    genre->setText(movie.getGenre().c_str());
    likes->setText(std::to_string(movie.getLikes()).c_str());
    releaseYear->setText(std::to_string(movie.getReleaseYear()).c_str());

    previewTrailer(movie.getTrailerLink());
}

void SearchTab::previewTrailer(string trailer) {
    string command = "open " + trailer;
    try {
        system(command.c_str());
    } catch (std::exception &ex) {
        warn(ex.what());
    }
}
