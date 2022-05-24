//
// Created by Teodor Filp on 25/04/2021.
//

#pragma once
#ifndef TESTQT_ADDMOVIETAB_H
#define TESTQT_ADDMOVIETAB_H

#include <string>
#include <QFormLayout>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>
#include "../../../services/ManagerService.h"
#include "../../../services/IUndoRedoService.h"
//#include "./MainScreen.h"

using std::string;

//typedef void (MainScreen::*AddNewMovie)(string, string, string, int, int);

class AddMovieTab: public QWidget {
    IUndoRedoService *undoService;
    QGridLayout *gridLayout;
    QLabel *titleLabel;
    QLabel *genreLabel;
    QLabel *trailerLabel;
    QLabel *releaseYearLabel;
    QLabel *likesLabel;
    QLineEdit *titleField;
    QLineEdit *genreField;
    QLineEdit *trailerField;
    QLineEdit *releaseYearField;
    QLineEdit *likesField;
    QPushButton *saveButton;

    ManagerService *managerService;

    void setupUi();

    public:
        AddMovieTab(ManagerService*, IUndoRedoService*);
        void onClickSave();
        void clearFields();
        void warn(string);
};

#endif //TESTQT_ADDMOVIETAB_H
