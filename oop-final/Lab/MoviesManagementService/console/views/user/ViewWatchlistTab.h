//
// Created by Teodor Filp on 01/05/2021.
//

#ifndef TESTQT_VIEWWATCHLISTTAB_H
#define TESTQT_VIEWWATCHLISTTAB_H

#include <QGridLayout>
#include <QTableWidget>
#include <QMessageBox>

#include "../../models/MovieModel.h"

class UserService;

namespace User {
    class ViewWatchlistTab: public QWidget {
        private:
            UserService *userService;
            QGridLayout *gridLayout;
            QTableWidget *tableWidget;
            QTableView *tableView;
            MovieModel *movieModel;
        public:
            ViewWatchlistTab(UserService*);
            void setupUi();
            void loadData();
            void addTableOptions();
            void addTableHeader();
            void handleDelete(int);
            void handleCellClicked();
            QTableWidgetItem *applyCentering(QTableWidgetItem *pItem);
            int warn(Movie&, string, string, QMessageBox::StandardButton, QMessageBox::StandardButton);
            int warnDelete(Movie&);
            int warnRate(Movie&);
    };
}

#endif //TESTQT_VIEWWATCHLISTTAB_H
