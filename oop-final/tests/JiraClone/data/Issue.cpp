//
// Created by Teodor Filp on 21/06/2021.
//

#include "Issue.h"

Issue::Issue(string &description, string &reporter, string &status, string &solver) {
    this->description = description;
    this->reporter = reporter;
    this->status = status;
    this->solver = solver;
}

void Issue::solve(string &solver) {
    if (this->status == OPEN) {
        this->solver = solver;
        this->status = CLOSED;
    }
}

const string &Issue::getDescription() const {
    return description;
}

const string &Issue::getReporter() const {
    return reporter;
}

const string &Issue::getStatus() const {
    return status;
}

const string &Issue::getSolver() const {
    return solver;
}
