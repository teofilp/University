//
// Created by Teodor Filp on 25/04/2021.
//

#pragma once
#ifndef TESTQT_CHOOSEMODEVIEW_H
#define TESTQT_CHOOSEMODEVIEW_H

#include <QMainWindow>
#include <QGridLayout>
#include <QPushButton>

class GuiApp;

class ChooseModeView: public QWidget {
    private:
        GuiApp *parent;
        QGridLayout *grid;
        QPushButton *chooseManagerButton;
        QPushButton *chooseUserButton;

        void setupUi();
    public:
        ChooseModeView(GuiApp*);
        ~ChooseModeView();

};

#endif //TESTQT_CHOOSEMODEVIEW_H
