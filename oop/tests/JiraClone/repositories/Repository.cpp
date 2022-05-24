//
// Created by Teodor Filp on 21/06/2021.
//
#include <fstream>
#include "Repository.h"

using std::ifstream;

Repository::Repository() {
    loadData();
}

void Repository::loadData() {
    ifstream read(teamFile);

    int teamSize;
    read >> teamSize;

    while(teamSize-- > 0) {
        string name, type;
        read >> name >> type;

        team.push_back(User(name, type));
    }

    read.close();

    read.open(issuesFile);

    int issuesSize;
    read >> issuesSize;

    while (issuesSize-- > 0) {
        string description, reporter, status, solver;
        read >> description >> reporter >> status >> solver;

        issues.push_back(Issue(description, reporter, status, solver));
    }
}

vector<User> &Repository::getTeam() {
    return this->team;
}

vector<Issue> &Repository::getIssues() {
    return this->issues;
}

void Repository::addIssue(string desc, User *reporter) {
    string solver = "n/a";
    string status = OPEN;
    string description = desc;
    string reporterName = reporter->getName();

    auto newIssue = Issue(description, reporterName, status, solver);

    issues.push_back(newIssue);
    this->notify();
}

void Repository::solveIssue(int index, User *solver) {
    string solverName = solver->getName();
    this->issues[index].solve(solverName);

    this->notify();
}

void Repository::deleteIssue(int index) {
    this->issues.erase(this->issues.begin()+index);
    this->notify();
}

