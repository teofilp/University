//
// Created by Teodor Filp on 25/04/2021.
//

#include <iostream>
#include <string>
#include <QStyle>
#include <QMessageBox>

#include "./UpdateMovieTab.h"

using std::string;

UpdateMovieTab::UpdateMovieTab(ManagerService *service, IUndoRedoService *undoRedoService) {
    undoService = undoRedoService;
    managerService = service;
    setupUi();
}

void UpdateMovieTab::setupUi() {
    gridLayout = new QGridLayout();
    idField = new QLineEdit();
    titleField = new QLineEdit();
    genreField = new QLineEdit();
    trailerField = new QLineEdit();
    releaseYearField = new QLineEdit();
    likesField = new QLineEdit();

    idLabel = new QLabel("Id");
    titleLabel = new QLabel("Title");
    genreLabel = new QLabel("Genre");
    trailerLabel = new QLabel("Trailer");
    releaseYearLabel = new QLabel("Release Year");
    likesLabel = new QLabel("Likes");
    saveButton = new QPushButton("Save");

    gridLayout->addWidget(idLabel, 1, 1);
    gridLayout->addWidget(idField, 1, 2);
    gridLayout->addWidget(titleLabel, 2, 1);
    gridLayout->addWidget(titleField, 2, 2);
    gridLayout->addWidget(genreLabel, 2, 3);
    gridLayout->addWidget(genreField, 2, 4);
    gridLayout->addWidget(trailerLabel, 3, 1);
    gridLayout->addWidget(trailerField, 3, 2, 1, 3);
    gridLayout->addWidget(releaseYearLabel, 4, 1);
    gridLayout->addWidget(releaseYearField, 4, 2);
    gridLayout->addWidget(likesLabel, 4, 3);
    gridLayout->addWidget(likesField, 4, 4);
    gridLayout->addWidget(saveButton, 5, 4);

    this->setLayout(gridLayout);
    QObject::connect(saveButton, &QPushButton::clicked, this, &UpdateMovieTab::onClickSave);
    QObject::connect(idField, &QLineEdit::textChanged, this, &UpdateMovieTab::onIdFieldChanged);
}

void UpdateMovieTab::onClickSave() {
    int id = idField->text().toInt();
    string title = titleField->text().toStdString();
    string genre = genreField->text().toStdString();
    string trailer = trailerField->text().toStdString();
    int releaseYear = releaseYearField->text().toInt();
    int likes = likesField->text().toInt();
    printf("Title: %s\nGenre: %s\nTrailer: %s\nRelease Year: %d\nLikes: %d\n",
           title.c_str(), genre.c_str(), trailer.c_str(), releaseYear, likes);
    Movie m(title, genre, trailer, releaseYear, likes);
    m.id = id;
    try {
        managerService->update(m);
        undoService->markCheckpoint();
    } catch (std::exception &ex) {
        warn(ex.what());
    }
    clearFields();
}

void UpdateMovieTab::onIdFieldChanged(const QString &text) {
    std::cout << text.toStdString();
    auto id = text.toInt();
    Movie movie;
    try {
       movie = managerService->getById(id);
       saveButton->setDisabled(false);
    } catch (...) {
        movie = Movie("", "", "", 0, 0);
        saveButton->setDisabled(true);
    }

    titleField->setText(QString(movie.getTitle().c_str()));
    genreField->setText(QString(movie.getGenre().c_str()));
    trailerField->setText(QString(movie.getTrailerLink().c_str()));
    releaseYearField->setText(QString(std::to_string(movie.getReleaseYear()).c_str()));
    likesField->setText(QString(std::to_string(movie.getLikes()).c_str()));
}

void UpdateMovieTab::clearFields() {
    idField->setText("");
    titleField->setText("");
    genreField->setText("");
    trailerField->setText("");
    releaseYearField->setText("");
    likesField->setText("");
}

void UpdateMovieTab::warn(string message) {
    QMessageBox msgBox;
    msgBox.setText(message.c_str());
    msgBox.exec();
}
