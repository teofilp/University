//
// Created by Teodor Filp on 11/05/2021.
//

#ifndef TESTQT_PREVIEWWATCHLISTTAB_H
#define TESTQT_PREVIEWWATCHLISTTAB_H


#include <QWidget>
#include <QLineEdit>
#include <QPushButton>
#include <QGridLayout>
#include "../../../services/UserService.h"

class PreviewWatchlistTab: public QWidget {
    private:
        UserService *userService;
        QLineEdit *input;
        QPushButton *htmlBtn;
        QPushButton *csvBtn;
        QGridLayout *layout;

    public:
        PreviewWatchlistTab(UserService*);
        void previewWithCSV();
        void previewWithHTML();
        void warn(string);

    void setupLayout();
};


#endif //TESTQT_PREVIEWWATCHLISTTAB_H
