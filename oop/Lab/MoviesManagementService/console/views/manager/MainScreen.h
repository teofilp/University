//
// Created by Teodor Filp on 25/04/2021.
//

#pragma once
#ifndef TESTQT_MANAGER_MAINSCREEN_H
#define TESTQT_MANAGER_MAINSCREEN_H
#include <string>
#include <QWidget>
#include <QTabWidget>
#include <QGridLayout>
#include "AddMovieTab.h"
#include "UpdateMovieTab.h"
#include "ViewMoviesTab.h"
#include "../../../services/IUndoRedoService.h"

class QKeyEvent;

using std::string;

namespace Manager {
    class MainScreen : public QWidget {
    private:
        IUndoRedoService *undoService;
        QTabWidget *tabWidget;
        QGridLayout *gridLayout;
        AddMovieTab *addMovieScreen;
        UpdateMovieTab *updateMovieScreen;
        ViewMoviesTab *viewMoviesScreen;
        QPushButton *undoBtn, *redoBtn;

        void addTabs();
        void handleTabChanged(int current);
        void handleRedo();
        void handleUndo();
        void keyPressEvent(QKeyEvent*) override;
    public:
        MainScreen(ManagerService*, IUndoRedoService*);
    };
}


#endif //TESTQT_MAINSCREEN_H
