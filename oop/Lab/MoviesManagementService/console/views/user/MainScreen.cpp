//
// Created by Teodor Filp on 01/05/2021.
//

#include "MainScreen.h"
#include "ViewWatchlistTab.h"
#include <iostream>

using namespace User;

MainScreen::MainScreen(UserService *service) {
    tabWidget = new QTabWidget();
    gridLayout = new QGridLayout();
    viewWatchlist = new ViewWatchlistTab(service);
    previewTab = new PreviewWatchlistTab(service);
    searchTab = new SearchTab(service);
    gridLayout->addWidget(tabWidget);
    addTabs();

    this->setLayout(gridLayout);
    this->resize(800, 400);
}

void MainScreen::addTabs() {
    tabWidget->addTab(viewWatchlist, "Watchlist");
    tabWidget->addTab(searchTab, "Search");
    tabWidget->addTab(previewTab, "Preview");

    QObject::connect(tabWidget, &QTabWidget::currentChanged, this, &MainScreen::handleTabChanged);
}

void MainScreen::handleTabChanged(int current) {
    std::cout << current;
    switch(current) {
        case 0: return viewWatchlist->loadData();
    }
}