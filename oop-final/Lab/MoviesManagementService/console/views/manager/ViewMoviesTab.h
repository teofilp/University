//
// Created by Teodor Filp on 25/04/2021.
//

#ifndef TESTQT_VIEWMOVIESTAB_H
#define TESTQT_VIEWMOVIESTAB_H


#include <QTableWidget>
#include <QGridLayout>
#include "../../../services/ManagerService.h"
#include "../../../services/IUndoRedoService.h"

namespace Manager {
    class ViewMoviesTab: public QWidget {
        private:
            IUndoRedoService *undoService;
            ManagerService *managerService;
            QGridLayout *gridLayout;
            QTableWidget *tableWidget;

        public:
            ViewMoviesTab(ManagerService*, IUndoRedoService*);
            void setupUi();
            void loadData();
            void addTableOptions();
            void addTableHeader();
            void handleDelete(int);
            void handleCellClicked();
            QTableWidgetItem *applyCentering(QTableWidgetItem *pItem);

        int warnDeleteMovie(Movie &movie);
    };
}


#endif //TESTQT_VIEWMOVIESTAB_H
