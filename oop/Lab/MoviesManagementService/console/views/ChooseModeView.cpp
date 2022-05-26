//
// Created by Teodor Filp on 25/04/2021.
//
#include <QGridLayout>
#include <QPushButton>
#include "../GuiApp.h"
#include "ChooseModeView.h"

ChooseModeView::ChooseModeView(GuiApp *app) {
    parent = app;
    setupUi();
}

void ChooseModeView::setupUi() {
    grid = new QGridLayout();
    chooseManagerButton = new QPushButton("Manager");
    chooseUserButton = new QPushButton("User");

    grid->addWidget(chooseManagerButton, 1, 1);
    grid->addWidget(chooseUserButton, 2, 1);

    this->resize(400, 150);
    this->setLayout(grid);

    QObject::connect(chooseUserButton, &QPushButton::clicked, parent, &GuiApp::handleChangeToUser);
    QObject::connect(chooseManagerButton, &QPushButton::clicked, parent, &GuiApp::handleChangeToManager);

}

ChooseModeView::~ChooseModeView() {}