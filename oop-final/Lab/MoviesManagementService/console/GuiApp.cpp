//
// Created by Teodor Filp on 25/04/2021.
//
#include <QApplication>
#include <string>
#include <fstream>
#include <streambuf>
#include "./GuiApp.h"
#include "../services/IUndoRedoService.h"

GuiApp::GuiApp(int argc, char *argv[], ManagerService *managerService, UserService *userService, IUndoRedoService *undoService) {
    managerService = managerService;
    undoRedoService = undoService;
    application = new QApplication(argc, argv);
    chooseModeView = new ChooseModeView(this);
    managerScreen = new Manager::MainScreen(managerService, undoRedoService);
    userScreen = new User::MainScreen(userService);
    chooseModeView->show();
    applyStyles();
}

void GuiApp::applyStyles() {
    std::ifstream t("../styles.css");
    std::string styles((std::istreambuf_iterator<char>(t)),
                    std::istreambuf_iterator<char>());
    application->setStyleSheet(styles.c_str());
}

int GuiApp::handleChangeToUser() {
    userScreen->show();
}

int GuiApp::handleChangeToManager() {
//    chooseModeView->hide();
    managerScreen->show();
}

int GuiApp::run() {
    return QApplication::exec();
}
