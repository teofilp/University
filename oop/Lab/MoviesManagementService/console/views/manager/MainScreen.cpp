//
// Created by Teodor Filp on 25/04/2021.
//

#include <QLabel>
#include <iostream>
#include <QShortcut>
#include "MainScreen.h"
#include "../user/MainScreen.h"
#include <QKeyEvent>

using namespace Manager;

MainScreen::MainScreen(ManagerService *service, IUndoRedoService *undoRedoService) {
    undoService = undoRedoService;
    tabWidget = new QTabWidget();
    gridLayout = new QGridLayout();
    addMovieScreen = new AddMovieTab(service, undoService);
    updateMovieScreen = new UpdateMovieTab(service, undoService);
    viewMoviesScreen = new ViewMoviesTab(service, undoService);
    undoBtn = new QPushButton("Undo");
    redoBtn = new QPushButton("Redo");
    gridLayout->addWidget(undoBtn, 1, 1);
    gridLayout->addWidget(redoBtn, 1, 5);
    gridLayout->addWidget(tabWidget, 2, 1, 1, 5);

    addTabs();

    QObject::connect(tabWidget, &QTabWidget::currentChanged, this, &MainScreen::handleTabChanged);
    QObject::connect(undoBtn, &QPushButton::clicked, this, &MainScreen::handleUndo);
    QObject::connect(redoBtn, &QPushButton::clicked, this, &MainScreen::handleRedo);

    this->setLayout(gridLayout);
    this->resize(800, 400);
}

void MainScreen::addTabs() {
    tabWidget->addTab(viewMoviesScreen, "View List");
    tabWidget->addTab(addMovieScreen, "Add Movie");
    tabWidget->addTab(updateMovieScreen, "Update Movie");
}

void MainScreen::handleTabChanged(int current) {
    switch (current) {
        case 0: return viewMoviesScreen->loadData();
        case 1: return addMovieScreen->clearFields();
        case 2: return updateMovieScreen->clearFields();
    }
}

void MainScreen::handleRedo() {
    undoService->redo();
    viewMoviesScreen->loadData();
}

void MainScreen::handleUndo() {
    undoService->undo();
    viewMoviesScreen->loadData();
}

void MainScreen::keyPressEvent(QKeyEvent *event) {
    if (event->modifiers() & Qt::ControlModifier) {
        if (event->key() == Qt::Key_Z) {
            this->handleUndo();
        } else if (event->key() == Qt::Key_Y) {
            this->handleRedo();
        }
    }
}
