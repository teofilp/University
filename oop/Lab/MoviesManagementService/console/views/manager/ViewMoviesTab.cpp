//
// Created by Teodor Filp on 25/04/2021.
//

#include <QHeaderView>
#include "ViewMoviesTab.h"
#include <iostream>
#include <QPushButton>
#include <QMessageBox>

#define COLUMNS 7

using namespace Manager;

ViewMoviesTab::ViewMoviesTab(ManagerService *service, IUndoRedoService *undoRedoService) {
    undoService = undoRedoService;
    managerService = service;
    gridLayout = new QGridLayout();
    tableWidget = new QTableWidget();

    setupUi();
}

void ViewMoviesTab::setupUi() {
    tableWidget->setColumnCount(COLUMNS);
    gridLayout->addWidget(tableWidget);
    loadData();
    this->setLayout(gridLayout);
}

void ViewMoviesTab::loadData() {
    auto movies = managerService->getAll();
    tableWidget->clearContents();
    tableWidget->setRowCount(movies.size());
    std::cout << movies.size();
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

        QObject::connect(deleteBtn, &QPushButton::clicked, this, &ViewMoviesTab::handleCellClicked);
    }
    addTableHeader();
    addTableOptions();
}

void ViewMoviesTab::addTableHeader() {
    QStringList headers;
    headers << "#" << "Title" << "Genre" << "Trailer" << "Release Year" << "Likes" << "Actions";
    tableWidget->setHorizontalHeaderLabels(headers);
}

void ViewMoviesTab::addTableOptions() {
    tableWidget->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    tableWidget->setEditTriggers(QAbstractItemView::NoEditTriggers);
    tableWidget->setAlternatingRowColors(true);
    tableWidget->setSelectionMode(QAbstractItemView::NoSelection);
}

void ViewMoviesTab::handleCellClicked() {
    QVariant propertyV = sender()->property("row");
    if (propertyV.isValid()) {
        int row = propertyV.toInt();
        handleDelete(row);
    }
}

void ViewMoviesTab::handleDelete(int row) {
    Movie &movie = managerService->getAll()[row];
    int shouldDelete = warnDeleteMovie(movie);
    if (shouldDelete) {
        managerService->remove(movie.id);
        undoService->markCheckpoint();
        loadData();
    }
}

QTableWidgetItem *ViewMoviesTab::applyCentering(QTableWidgetItem *pItem) {
    pItem->setTextAlignment(Qt::AlignCenter);
    return pItem;
}

int ViewMoviesTab::warnDeleteMovie(Movie &movie) {
    QMessageBox msg;
    msg.setText("You are trying to delete a movie");
    msg.setInformativeText(("Are you sure you want to delete " + movie.getTitle() + "?").c_str());
    msg.setStandardButtons(QMessageBox::Save | QMessageBox::Cancel);
    int returnValue = msg.exec();

    switch (returnValue) {
        case QMessageBox::Save: return 1;
        case QMessageBox::Cancel: return 0;
        default: throw std::runtime_error("invalid code");
    }
}


