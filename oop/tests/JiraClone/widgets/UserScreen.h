//
// Created by Teodor Filp on 21/06/2021.
//

#ifndef JIRACLONE_USERSCREEN_H
#define JIRACLONE_USERSCREEN_H


#include <QWidget>
#include <QGridLayout>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>
#include <QTableWidget>
#include "../data/User.h"
#include "../repositories/Repository.h"

class UserScreen: public QWidget, public Subscriber {
    private:
        QGridLayout *layout;
        QLabel *label;
        QLineEdit *addIssueDesc;
        QPushButton *addIssueBtn;
        QTableWidget *table;

        Repository *repository;
        User *user;

public:
        UserScreen(User*, Repository*);

        void setupUI();

        bool isTester();

        void addIssue();

        void loadTable();

        void solveIssue();

        void deleteIssue();

        virtual void update() override;

};


#endif //JIRACLONE_USERSCREEN_H
