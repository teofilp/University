//
// Created by Teodor Filp on 25/04/2021.
//

#pragma once
#ifndef TESTQT_UPDATEMOVIETAB_H
#define TESTQT_UPDATEMOVIETAB_H

#include <string>
#include <QFormLayout>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>
#include "../../../services/ManagerService.h"
#include "../../../services/IUndoRedoService.h"

using std::string;

class UpdateMovieTab: public QWidget {
    IUndoRedoService *undoService;
    QGridLayout *gridLayout;
    QLabel *idLabel;
    QLabel *titleLabel;
    QLabel *genreLabel;
    QLabel *trailerLabel;
    QLabel *releaseYearLabel;
    QLabel *likesLabel;
    QLineEdit *idField;
    QLineEdit *titleField;
    QLineEdit *genreField;
    QLineEdit *trailerField;
    QLineEdit *releaseYearField;
    QLineEdit *likesField;
    QPushButton *saveButton;

    ManagerService *managerService;

    void setupUi();

public:
    UpdateMovieTab(ManagerService*, IUndoRedoService*);
    void onClickSave();
    void clearFields();
    void onIdFieldChanged(const QString&);
    void warn(string);
};
#endif //TESTQT_UPDATEMOVIETAB_H
