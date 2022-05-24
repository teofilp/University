//
// Created by Teodor Filp on 21/06/2021.
//

#ifndef JIRACLONE_REPOSITORY_H
#define JIRACLONE_REPOSITORY_H

#include <vector>
#include <string>
#include "../data/User.h"
#include "../data/Issue.h"
#include "../Observable.h"

using std::vector;

class Repository: public Observable {
    private:
        vector<User> team;
        vector<Issue> issues;
        const string teamFile = "team.txt", issuesFile = "issues.txt";
    public:
        Repository();
        void loadData();
        vector<User>& getTeam();
        vector<Issue>& getIssues();
        void addIssue(string desc, User*);
        void solveIssue(int index, User*);
        void deleteIssue(int index);
};


#endif //JIRACLONE_REPOSITORY_H
