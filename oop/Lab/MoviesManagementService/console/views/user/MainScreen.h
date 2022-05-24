//
// Created by Teodor Filp on 01/05/2021.
//

#ifndef TESTQT_USER_MAINSCREEN_H
#define TESTQT_USER_MAINSCREEN_H

#include <QWidget>
#include <QTabWidget>
#include <QGridLayout>
#include "../../../services/UserService.h"
#include "SearchTab.h"
#include "ViewWatchlistTab.h"
#include "PreviewWatchlistTab.h"

namespace User {
    class MainScreen: public QWidget {
        private:
            QTabWidget *tabWidget;
            QGridLayout *gridLayout;
            ViewWatchlistTab *viewWatchlist;
            PreviewWatchlistTab *previewTab;
            SearchTab *searchTab;

            void addTabs();
            void handleTabChanged(int);

        public:
            MainScreen(UserService*);
    };
}

#endif //TESTQT_MAINSCREEN_H
