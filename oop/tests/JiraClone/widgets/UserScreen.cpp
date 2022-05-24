//
// Created by Teodor Filp on 21/06/2021.
//

#include <QMessageBox>
#include "UserScreen.h"

UserScreen::UserScreen(User *user, Repository *repo) {
    this->user = user;
    this->repository = repo;
    this->repository->subscribe(this);

    setupUI();
}

void UserScreen::setupUI() {
    layout = new QGridLayout();
    label = new QLabel((user->getName() + "-" + user->getType()).c_str());
    table = new QTableWidget();
    if (isTester()) {
        addIssueDesc = new QLineEdit();
        addIssueDesc->setPlaceholderText("Issue descrption");
        addIssueBtn = new QPushButton("Add");

        layout->addWidget(addIssueDesc, 2, 1);
        layout->addWidget(addIssueBtn, 2, 2);

        QObject::connect(addIssueBtn, &QPushButton::clicked, this, &UserScreen::addIssue);
    }

    layout->addWidget(label, 1, 1);
    layout->addWidget(table, 3, 1, 1, 2);

    this->setMinimumSize(700, 200);
    this->setLayout(layout);
    this->show();
    loadTable();
}

bool UserScreen::isTester() {
    return user->getType() == TESTER;
}

void UserScreen::addIssue() {
    string desc = addIssueDesc->text().toStdString();
    if (desc.size() == 0) {
        QMessageBox msgBox;
        msgBox.setText("Issue description cannot be empty");
        msgBox.setDefaultButton(QMessageBox::Ok);
        msgBox.exec();
        return;
    }

    repository->addIssue(desc, user);

    addIssueDesc->clear();
}

void UserScreen::loadTable() {
    table->clear();
    table->setRowCount(repository->getIssues().size());
    if (isTester()) {
        table->setColumnCount(5);
    } else {
        table->setColumnCount(6);
    }

    for (int i=0; i<repository->getIssues().size(); i++) {
        auto issue = repository->getIssues()[i];

        QPushButton *deleteBtn = new QPushButton("Delete");
        deleteBtn->setDisabled(issue.getStatus() == OPEN);
        deleteBtn->setProperty("id", i);

        table->setItem(i, 0, new QTableWidgetItem(issue.getDescription().c_str()));
        table->setItem(i, 1, new QTableWidgetItem(issue.getReporter().c_str()));
        table->setItem(i, 2, new QTableWidgetItem(issue.getStatus().c_str()));
        table->setItem(i, 3, new QTableWidgetItem(issue.getSolver().c_str()));

        if (!isTester()) {
            QPushButton *solveBtn = new QPushButton("Solve");
            solveBtn->setDisabled(issue.getStatus() == CLOSED);
            solveBtn->setProperty("id", i);

            table->setCellWidget(i, 4, solveBtn);
            QObject::connect(solveBtn, &QPushButton::clicked, this, &UserScreen::solveIssue);
        }

        int deleteCol = isTester() ? 4 : 5;
        table->setCellWidget(i, deleteCol, deleteBtn);
        QObject::connect(deleteBtn, &QPushButton::clicked, this, &UserScreen::deleteIssue);
    }
}

void UserScreen::update() {
    this->loadTable();
}

void UserScreen::solveIssue() {
    QVariant propertyV = sender()->property("id");
    if (propertyV.isValid()) {
        int id = propertyV.toInt();
        repository->solveIssue(id, user);
    }
}

void UserScreen::deleteIssue() {
    QVariant propertyV = sender()->property("id");
    if (propertyV.isValid()) {
        int id = propertyV.toInt();
        repository->deleteIssue(id);
    }
}

