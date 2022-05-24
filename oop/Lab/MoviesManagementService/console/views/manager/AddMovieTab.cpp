//
// Created by Teodor Filp on 25/04/2021.
//
#include <iostream>
#include <string>
#include <QStyle>
#include <QMessageBox>

#include "./AddMovieTab.h"
#include "../../../services/IUndoRedoService.h"

using std::string;

AddMovieTab::AddMovieTab(ManagerService *service, IUndoRedoService *undoRedoService) {
    managerService = service;
    undoService = undoRedoService;
    setupUi();
}

void AddMovieTab::setupUi() {
    gridLayout = new QGridLayout();
    titleField = new QLineEdit();
    genreField = new QLineEdit();
    trailerField = new QLineEdit();
    releaseYearField = new QLineEdit();
    likesField = new QLineEdit();

    titleLabel = new QLabel("Title");
    genreLabel = new QLabel("Genre");
    trailerLabel = new QLabel("Trailer");
    releaseYearLabel = new QLabel("Release Year");
    likesLabel = new QLabel("Likes");
    saveButton = new QPushButton("Save");

    gridLayout->addWidget(titleLabel, 1, 1);
    gridLayout->addWidget(titleField, 1, 2);
    gridLayout->addWidget(genreLabel, 1, 3);
    gridLayout->addWidget(genreField, 1, 4);
    gridLayout->addWidget(trailerLabel, 2, 1);
    gridLayout->addWidget(trailerField, 2, 2, 1, 3);
    gridLayout->addWidget(releaseYearLabel, 3, 1);
    gridLayout->addWidget(releaseYearField, 3, 2);
    gridLayout->addWidget(likesLabel, 3, 3);
    gridLayout->addWidget(likesField, 3, 4);
    gridLayout->addWidget(saveButton, 4, 4);

    this->setLayout(gridLayout);
    QObject::connect(saveButton, &QPushButton::clicked, this, &AddMovieTab::onClickSave);
}

void AddMovieTab::onClickSave() {
    string title = titleField->text().toStdString();
    string genre = genreField->text().toStdString();
    string trailer = trailerField->text().toStdString();
    int releaseYear = releaseYearField->text().toInt();
    int likes = likesField->text().toInt();
    printf("Title: %s\nGenre: %s\nTrailer: %s\nRelease Year: %d\nLikes: %d\n",
           title.c_str(), genre.c_str(), trailer.c_str(), releaseYear, likes);
    Movie m(title, genre, trailer, releaseYear, likes);
    try {
        managerService->add(m);
        undoService->markCheckpoint();
    } catch(std::exception &ex) {
        warn(ex.what());
    }
    clearFields();
}

void AddMovieTab::clearFields() {
    titleField->setText("");
    genreField->setText("");
    trailerField->setText("");
    releaseYearField->setText("");
    likesField->setText("");
}

void AddMovieTab::warn(string message) {
    QMessageBox msgBox;
    msgBox.setText(message.c_str());
    msgBox.exec();
}
