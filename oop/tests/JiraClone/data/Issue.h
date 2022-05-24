//
// Created by Teodor Filp on 21/06/2021.
//

#ifndef JIRACLONE_ISSUE_H
#define JIRACLONE_ISSUE_H
#include <string>
using std::string;

const string OPEN = "open";
const string CLOSED = "closed";

class Issue {
private:
public:
    const string &getDescription() const;

    const string &getReporter() const;

    const string &getStatus() const;

    const string &getSolver() const;

private:
    string description, reporter, status, solver;
public:
    Issue(string& description, string& reporter, string& status, string& solver);
    void solve(string &solver);
};


#endif //JIRACLONE_ISSUE_H
