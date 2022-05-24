//
// Created by Teodor Filp on 01/05/2021.
//

#include <iostream>
#include <QHeaderView>
#include <QMessageBox>
#include <QPushButton>
#include "ViewWatchlistTab.h"
#include "../../../services/UserService.h"

#define COLUMNS 7

using namespace User;

ViewWatchlistTab::ViewWatchlistTab(UserService *service) {
    userService = service;
    gridLayout = new QGridLayout();
    tableWidget = new QTableWidget();
    movieModel = new MovieModel(service->getWatchlist());
    tableView = new QTableView();
    tableView->setModel(movieModel);

    setupUi();
}

void ViewWatchlistTab::setupUi() {
    tableWidget->setColumnCount(COLUMNS);
    loadData();
//    gridLayout->addWidget(tableWidget);
    gridLayout->addWidget(tableView);
    this->setLayout(gridLayout);
}

void ViewWatchlistTab::loadData() {
    movieModel->reset(userService->getWatchlist());
    return;
    auto movies = userService->getWatchlist();

    tableWidget->setRowCount(movies.size());
    tableWidget->clear();

    for (int i=0; i<movies.size(); i++) {
        QPushButton *deleteBtn = new QPushButton("Delete");
        auto movie = movies[i];
        deleteBtn->setProperty("row", i);
        tableWidget->setItem(i, 0, applyCentering(new QTableWidgetItem(std::to_string(movie.id).c_str())));
        tableWidget->setItem(i, 1, applyCentering(new QTableWidgetItem(movie.getTitle().c_str())));
        tableWidget->setItem(i, 2, applyCentering(new QTableWidgetItem(movie.getGenre().c_str())));
        tableWidget->setItem(i, 3, applyCentering(new QTableWidgetItem(movie.getTrailerLink().c_str())));
        tableWidget->setItem(i, 4, applyCentering(new QTableWidgetItem(std::to_string(movie.getReleaseYear()).c_str())));
        tableWidget->setItem(i, 5, applyCentering(new QTableWidgetItem(std::to_string(movie.getLikes()).c_str())));
        tableWidget->setCellWidget(i, 6, deleteBtn);
        QObject::connect(deleteBtn, &QPushButton::clicked, this, &ViewWatchlistTab::handleCellClicked);
    }
    addTableHeader();
    addTableOptions();

    QObject::connect(tableWidget, &QTableWidget::cellClicked, this, &ViewWatchlistTab::handleCellClicked);
}

void ViewWatchlistTab::addTableOptions() {
    tableWidget->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    tableWidget->setEditTriggers(QAbstractItemView::NoEditTriggers);
    tableWidget->setAlternatingRowColors(true);
    tableWidget->setSelectionMode(QAbstractItemView::NoSelection);
}

void ViewWatchlistTab::addTableHeader() {
    QStringList headers;
    headers << "#" << "Title" << "Genre" << "Trailer" << "Release Year" << "Likes" << "Actions";
    tableWidget->setHorizontalHeaderLabels(headers);
}

void ViewWatchlistTab::handleDelete(int row) {
    using namespace std;
    Movie movie = userService->getWatchlist()[row];
    int shouldDelete = warnDelete(movie);
    if (shouldDelete) {
        int shouldRate = warnRate(movie);
        if (shouldRate) {
            userService->increaseLikes(movie.id);
        }
        userService->removeFromWatchlist(movie.id);
        loadData();
    }
}

void ViewWatchlistTab::handleCellClicked() {
    QVariant propertyV = sender()->property("row");
    if (propertyV.isValid()) {
        int row = propertyV.toInt();
        handleDelete(row);
    }
}

QTableWidgetItem *ViewWatchlistTab::applyCentering(QTableWidgetItem *pItem) {
    pItem->setTextAlignment(Qt::AlignCenter);
    return pItem;
}

int ViewWatchlistTab::warn(Movie &movie, string title, string description, QMessageBox::StandardButton success = QMessageBox::Save, QMessageBox::StandardButton cancel = QMessageBox::Cancel) {
    QMessageBox msg;
    msg.setText(title.c_str());
    msg.setInformativeText(description.c_str());
    msg.setStandardButtons(success | cancel);
    int returnValue = msg.exec();

    if (returnValue == success) {
        return 1;
    } else if (returnValue == cancel) {
        return 0;
    } else {
        throw std::runtime_error("Invalid type");
    }
}

int ViewWatchlistTab::warnDelete(Movie &movie) {
    string title = "You are trying to delete a movie";
    string description = "Are you sure you want to delete " + movie.getTitle() + "?";
    return warn(movie, title, description);
}

int ViewWatchlistTab::warnRate(Movie &movie) {
    string title = "You are about to delete " + movie.getTitle();
    string description = "Do you want to rate it first?";
    return warn(movie, title, description, QMessageBox::Yes, QMessageBox::No);
}
