//
// Created by Teodor Filp on 21/06/2021.
//

#ifndef JIRACLONE_MAINWINDOW_H
#define JIRACLONE_MAINWINDOW_H

#include <vector>

using std::vector;

#include "UserScreen.h"
#include "../repositories/Repository.h"

class MainWindow: QWidget {
private:
    vector<UserScreen*> screens;
    Repository *repository;
public:
    MainWindow(Repository*);

    void loadTeam();
};


#endif //JIRACLONE_MAINWINDOW_H
