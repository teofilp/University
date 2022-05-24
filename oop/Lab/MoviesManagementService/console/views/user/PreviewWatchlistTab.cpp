//
// Created by Teodor Filp on 11/05/2021.
//

#include <QMessageBox>
#include "PreviewWatchlistTab.h"
#include "../../../factory/DataDisplayFactory.h"

PreviewWatchlistTab::PreviewWatchlistTab(UserService *service) {
    userService = service;
    setupLayout();

}

void PreviewWatchlistTab::setupLayout() {
    input = new QLineEdit();
    csvBtn = new QPushButton("CSV");
    htmlBtn = new QPushButton("HTML");
    layout = new QGridLayout();

    layout->addWidget(input, 1, 1, 1, 2);
    layout->addWidget(csvBtn, 2, 1, 1, 1);
    layout->addWidget(htmlBtn, 2, 2, 1, 1);

    setLayout(layout);

    QObject::connect(csvBtn, &QPushButton::clicked, this, &PreviewWatchlistTab::previewWithCSV);
    QObject::connect(htmlBtn, &QPushButton::clicked, this, &PreviewWatchlistTab::previewWithHTML);
}

void PreviewWatchlistTab::previewWithCSV() {
    string fileName = input->text().toStdString();
    if (fileName == "") {
        warn("file cannot be empty");
        return;
    }
    DataDisplayFactory factory;
    DataDisplayHandler *handler = factory.getDataDisplayHandler(CSV, fileName);
    userService->previewWatchlist(handler);
}

void PreviewWatchlistTab::previewWithHTML() {
    string fileName = input->text().toStdString();
    if (fileName == "") {
        warn("file cannot be empty");
        return;
    }
    DataDisplayFactory factory;
    DataDisplayHandler *handler = factory.getDataDisplayHandler(HTML, fileName);
    userService->previewWatchlist(handler);
}

void PreviewWatchlistTab::warn(string message) {
    QMessageBox msgBox;
    msgBox.setText(message.c_str());
    msgBox.exec();
}
