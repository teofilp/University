//
// Created by Teodor Filp on 21/06/2021.
//

#include "MainWindow.h"

MainWindow::MainWindow(Repository *repo) {
    repository = repo;
    loadTeam();
}

void MainWindow::loadTeam() {
    for (auto &user: repository->getTeam()) {
        screens.push_back(new UserScreen(&user, repository));
    }
}
