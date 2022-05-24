//
// Created by Teodor Filp on 25/04/2021.
//

#pragma once
#ifndef TESTQT_GUIAPP_H
#define TESTQT_GUIAPP_H
#include "../services/ManagerService.h"
#include "views/manager/MainScreen.h"
#include "views/manager/ViewMoviesTab.h"
#include "views/ChooseModeView.h"
#include "views/user/MainScreen.h"
#include "../services/IUndoRedoService.h"

class GuiApp: public QObject {
    private:
        QApplication *application;
        ChooseModeView *chooseModeView;
        Manager::MainScreen *managerScreen;
        User::MainScreen *userScreen;
        ManagerService *managerService;
        IUndoRedoService *undoRedoService;

        void applyStyles();
    public:
        GuiApp(int, char**, ManagerService*, UserService*, IUndoRedoService*);
        int handleChangeToUser();
        int handleChangeToManager();
        int run();
};


#endif //TESTQT_GUIAPP_H
