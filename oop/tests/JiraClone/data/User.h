//
// Created by Teodor Filp on 21/06/2021.
//

#ifndef JIRACLONE_USER_H
#define JIRACLONE_USER_H

#include <string>

using std::string;

const string PROGRAMMER = "programmer";
const string TESTER = "tester";

class User {
    private:
        string name, type;
    public:
        User(string& name, string& type);

    const string &getName() const;

    const string &getType() const;
};


#endif //JIRACLONE_USER_H
