//
// Created by Teodor Filp on 21/06/2021.
//

#include "User.h"

User::User(string &name, string &type) {
    this->name = name;
    this->type = type;
}

const string &User::getName() const {
    return name;
}

const string &User::getType() const {
    return type;
}
